package fr.eni.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.BLLException;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;

/**
 * Servlet implementation class seConnecter
 */
@WebServlet("/Connection")
public class ServletSeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String msg = "";
	Utilisateur user = new Utilisateur();
	HttpSession session;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/WEB-INF/ConnexionUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String Id_Saisie = request.getParameter("Identifiant");
		String Mdp_Saisie = request.getParameter("Mdp");

		// System.out.println(Id_Saisie);
		// System.out.println(Mdp_Saisie);

		// id est-il existant ?
		UtilisateurManager mgr = new UtilisateurManager();

		// Test des Identifiants utlilisateurs //
		// Utilisateur userPseudo = mgr.selectByPseudo(Id_Saisie);
		// Utilisateur userMail = mgr.selectByMail(Id_Saisie);
		String mdp_Compare;
		// String mdp_Compare2 = userMail.getMot_de_passe();

		// System.out.println(mdp_Compare);
		// System.out.println(Mdp_Saisie);
		// System.out.println(Id_Saisie);
		// System.out.println(mgr.selectByMail(Id_Saisie).getEmail());
		// System.out.println(Id_Saisie.equals(mgr.selectByMail(Id_Saisie).getEmail()));
		try {
			user = mgr.selectByPseudo(Id_Saisie);
			if (Id_Saisie.equals(user.getPseudo())) {

				mdp_Compare = user.getMot_de_passe();
				System.out.println("1er IF");

				if (Mdp_Saisie.equals(mdp_Compare)) {
					//session
					session=request.getSession();
					
					System.out.println(user.getNo_utilisateur());
			        session.setAttribute("user",user); 
					response.sendRedirect("Servlet");
				} else {
					msg = "Le mot de passe saisie n'est pas correct";
					doGet(request, response);
				}

			}
		} catch (BLLException e) {
			try {
				user = mgr.selectByMail(Id_Saisie);
				if (Id_Saisie.equals(user.getEmail())) {
					mdp_Compare = user.getMot_de_passe();
					if (Mdp_Saisie.equals(mdp_Compare)) {
						response.sendRedirect("Servlet");

					} else {
						msg = "Le mot de passe saisie n'est pas correct";
						doGet(request, response);
					}
				}
			} catch (BLLException k) {
				msg = "Identifiant Inconnu";
				doGet(request, response);
			}
		}
	}
}

// si oui le mdp saisie est-il celui de l'utilisateur existant
