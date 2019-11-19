/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.ws;

import com.mk.tss.entities.Bitacora;
import com.mk.tss.entities.Bomba;
import com.mk.tss.entities.Operador;
import com.mk.tss.entities.Usuario;
import com.mk.tss.dao.BitacoraFacadeLocal;
import com.mk.tss.dao.BombaFacadeLocal;
import com.mk.tss.dao.OperadorFacadeLocal;
import com.mk.tss.dao.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author WF Consulting
 */
public class DatosRemoto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    BombaFacadeLocal bombaFacadeLocal;
    @EJB
    BitacoraFacadeLocal bitacoraFacadeLocal;
    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    OperadorFacadeLocal operadorFacadeLocal;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //request.getContextPath() d=1|2|1231.123|2121.121|1|3|2132123/21232/121|321/4554/512||2121|
//68|1|19.293062|-99.656952|0|0|2019-7-25T15:46:4|2019-7-25T:15:47:28|1|12
            System.out.println("recibiendo datos ..........");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String usuario = request.getParameter("usu");
            String bomba = request.getParameter("bom");
            String latitud = request.getParameter("lat");
            String longitud = request.getParameter("lon");
            String contadorI = request.getParameter("cin");
            String contadorF = request.getParameter("cfi");
            String fechaini = request.getParameter("fin");
            String fechafin = request.getParameter("ffi");
            String numcont = request.getParameter("nen");
            if ((bomba != null && !bomba.equals("")) && (usuario != null && !usuario.equals("")) && (contadorI != null && !contadorI.equals("")) && (contadorF != null && !contadorF.equals("")) && (latitud != null && !latitud.equals("")) && (longitud != null && !longitud.equals("")) && (fechaini != null && !fechaini.equals("")) && (fechafin != null && !fechafin.equals("")) && (numcont != null && !numcont.equals(""))) {
                System.out.println("datos recibidos");
                try {
                    Integer us = Integer.parseInt(usuario.trim());
                    Integer numenv = Integer.parseInt(numcont);
                    Usuario user = usuarioFacadeLocal.buscarConteo(us);
                    List<Operador> ope= operadorFacadeLocal.buscarUsuario(user);
                    Bomba bombaB = new Bomba();
                    for(Operador op:ope){
                       bombaB = bombaFacadeLocal.buscarBomba(bomba, op); 
                       if(bombaB!=null){
                           break;
                       }
                    }
                    
                    if (bombaB != null) {
                        if (bitacoraFacadeLocal.buscarNumEnv(bombaB, numenv)) {
                            try {
                                //Date date = dateFormat.parse(dateString);
                                Integer contadorIn = Integer.parseInt(contadorI);
                                Integer contadorFi = Integer.parseInt(contadorF);
                                String ubicacion = latitud + "," + longitud;
                                System.out.println("Ubicacion de la bomba "+ ubicacion);
                                Bitacora bitacora = new Bitacora();
                                bitacora.setIdBomba(bombaB);
                                bitacora.setFecha(new Date());
                                bitacora.setUbicacion(ubicacion);
                                bitacora.setConteoInicial(contadorIn);
                                bitacora.setConteoFinal(contadorFi);
                                bitacora.setFechai(dateFormat.parse(fechaini));
                                bitacora.setFechaf(dateFormat.parse(fechafin));
                                bitacora.setNumenvio(numenv);
                                bitacoraFacadeLocal.insertar(bitacora);

                                out.println("<!DOCTYPE html>");
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<title>Correctos</title>");
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<h1>ok</h1>");
                                out.println("</body>");
                                out.println("</html>");
                            } catch (Exception ex) {

                                out.println("<!DOCTYPE html>");
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<title>Fallo</title>");
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<h1>fail</h1>");
                                out.println("</body>");
                                out.println("</html>");
                                ex.printStackTrace();
                            }
                        } else {
                            out.println("<!DOCTYPE html>");
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<title>Correctos</title>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<h1>ok</h1>");
                            out.println("</body>");
                            out.println("</html>");
                        }
                    }else{
                        System.out.println("no existe en la base de datos");
                    }
                } catch (Exception e) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>BD Fallo</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>fail</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    e.printStackTrace();
                }
            } else {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Sin datos</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>fail</h1>");
                out.println("</body>");
                out.println("</html>");
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
