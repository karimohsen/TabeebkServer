/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tabeebkServer.filter;

import com.tabeebkServer.pojo.Account;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Karim
 */
public class AdminFilter implements Filter{

    @Override
    public void init(FilterConfig fc) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest re = (HttpServletRequest) req;
            HttpSession session = re.getSession(false);
            Account typeId = (Account) session.getAttribute("account");
            if (typeId != null) {
                if (typeId.getAccounttype().getAccountTypeId() == 7) {
                    chain.doFilter(req, resp);//sends request to next resource
                } else {
                    ((HttpServletResponse) resp).setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

            } else {
                //out.print("username or password error!");
                ((HttpServletResponse) resp).setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

        } catch (Exception e) {
            ((HttpServletResponse) resp).setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
