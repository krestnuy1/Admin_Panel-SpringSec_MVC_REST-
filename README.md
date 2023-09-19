# Spring_Security_MVC_REST
An application template for building applications using a role-based access model. All requests are made using REST.
The web application does not reload pages for any CRUD operations.
The application supports two classes: User and Roles, which are connected through a @ManyToMany relationship.

1. Spring Security configuration has been set up.
2. A Role class has been created, and the User is linked to roles in a way that allows a user to have multiple roles.
3. The Role and User models have been implemented with the GrantedAuthority and UserDetails interfaces respectively.
4. All CRUD operations and their corresponding pages should only be accessible to users with an "admin" role via the URL: /admin/.
5. Users with a "user" role should only have access to their home page /user, where their data is displayed.
6. Access to this page should be granted only to users with "user" and "admin" roles.
7. Logout functionality has been configured on any page using Thymeleaf features.
8. The LoginSuccessHandler has been configured so that after authentication, an admin is redirected to the /admin page, and a user is directed to their /user page.

   ![Screenshot](https://github.com/krestnuy1/Admin_Panel-SpringSec_MVC_REST-/blob/master/Screenshots/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-09-19%20%D0%B2%2007.57.42.png)
   ![Screenshot](https://github.com/krestnuy1/Admin_Panel-SpringSec_MVC_REST-/blob/master/Screenshots/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-09-19%20%D0%B2%2007.57.46.png)
   ![Screenshot](https://github.com/krestnuy1/Admin_Panel-SpringSec_MVC_REST-/blob/master/Screenshots/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-09-19%20%D0%B2%2007.57.59.png)
   ![Screenshot](https://github.com/krestnuy1/Admin_Panel-SpringSec_MVC_REST-/blob/master/Screenshots/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-09-19%20%D0%B2%2007.58.31.png)
