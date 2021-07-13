package com.netcraker.controller;

//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RestController
//public class UserRestController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserController userController;
//
//    @Autowired
//    private ProductController productController;
//
//    @PostMapping(path = "/register", consumes = {
//            MediaType.APPLICATION_XML_VALUE,
//            MediaType.APPLICATION_JSON_VALUE
//    }, produces = {
//            MediaType.APPLICATION_XML_VALUE,
//            MediaType.APPLICATION_JSON_VALUE
//    })
//    public void addUser(@RequestBody User user, Model model, HttpServletResponse response) throws IOException {
//
//           System.out.println(user.getName());
//
//           if (!userService.saveUser(user)) {
//               model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
//               System.out.println("1");
//               response.sendRedirect("/register");
//               System.out.println("2");
//           }
////          else{    ModelAndView modelAndView = new ModelAndView();
////               modelAndView.setViewName("index");
////               return modelAndView;
////          }
//        response.sendRedirect("/index");
//
//    }
//}
