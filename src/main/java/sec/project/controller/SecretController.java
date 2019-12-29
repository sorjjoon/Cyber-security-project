package sec.project.controller;

import java.sql.SQLException;
import java.util.List;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import sec.project.data.UserStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.data.SecretStorage;
import sec.project.domain.Secret;

@Controller
public class SecretController {

    private final UserStorage userStorage;
    private final SecretStorage secretStorage;

    public SecretController() throws SQLException {
        super();
        secretStorage = new SecretStorage("./src/main/resources/data/secrets");
        this.userStorage = new UserStorage("./src/main/resources/data/users.txt");

    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String user, @RequestParam String password, @RequestParam String secret) {

        return "done";
    }

    @RequestMapping(params = "retrieve", value = "/login", method = RequestMethod.POST)
    public String getSecrets(Model model, HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userStorage.checkPassword(username, password)) {
            try {
                List<Secret> secrets = secretStorage.getSecrets(username);
              
                model.addAttribute("secrets",secrets);

                return "secrets";
            } catch (SQLException e) {

                return "error";
            }
        }
        return "wrongpassword";

    }

    @RequestMapping(params = "new", value = "/login", method = RequestMethod.POST)
    public String submitSecret(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String secret = request.getParameter("secret");
        if (userStorage.checkPassword(username, password)) {
            try {
                secretStorage.addSecret(secret, username);
                System.out.println(username + " added a secret");
        
                return "done";
            } catch (SQLException e) {

            }
        }
        return "error";

    }

}
