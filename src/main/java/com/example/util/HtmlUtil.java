package com.example.util;

import com.example.model.User;
import java.util.List;

public class HtmlUtil {

    public static String generateUserListHtml(List<User> users) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<title>User Management</title>")
                .append("<meta charset='UTF-8'>")
                .append("<style>")
                .append("body { font-family: Arial, sans-serif; margin: 40px; }")
                .append("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }")
                .append("th, td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }")
                .append("th { background-color: #f2f2f2; }")
                .append("form { display: inline; margin-right: 10px; }")
                .append("input { margin: 5px; padding: 5px; }")
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<h2>User Management</h2>")
                .append("<table>")
                .append("<tr><th>ID</th><th>Name</th><th>Email</th><th>Age</th><th>Actions</th></tr>");

        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                html.append("<tr>")
                        .append("<td>").append(user.getId()).append("</td>")
                        .append("<td>").append(user.getName()).append("</td>")
                        .append("<td>").append(user.getEmail()).append("</td>")
                        .append("<td>").append(user.getAge()).append("</td>")
                        .append("<td>")
                        .append("<form action='/SpringMVC_war_exploded/users/update' method='post'>")
                        .append("<input type='hidden' name='id' value='").append(user.getId()).append("'>")
                        .append("<input type='text' name='name' value='").append(user.getName()).append("' required>")
                        .append("<input type='email' name='email' value='").append(user.getEmail()).append("' required>")
                        .append("<input type='number' name='age' value='").append(user.getAge()).append("' required>")
                        .append("<button type='submit'>Update</button>")
                        .append("</form>")
                        .append("<form action='/SpringMVC_war_exploded/users/delete' method='post'>")
                        .append("<input type='hidden' name='id' value='").append(user.getId()).append("'>")
                        .append("<button type='submit'>Delete</button>")
                        .append("</form>")
                        .append("</td>")
                        .append("</tr>");
            }
        } else {
            html.append("<tr><td colspan='5'>No users found</td></tr>");
        }

        html.append("</table>")
                .append("<h3>Add New User</h3>")
                .append("<form action='/SpringMVC_war_exploded/users/add' method='post'>")
                .append("Name: <input type='text' name='name' required><br>")
                .append("Email: <input type='email' name='email' required><br>")
                .append("Age: <input type='number' name='age' required><br>")
                .append("<button type='submit'>Add User</button>")
                .append("</form>")
                .append("</body>")
                .append("</html>");

        return html.toString();
    }
}