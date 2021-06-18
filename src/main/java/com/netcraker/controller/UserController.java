package com.netcraker.controller;


import com.netcraker.model.User;
import com.netcraker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/greeting")
    public String hello(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "greeting";
    }
}

//    @PostMapping("/post")
//    public String post(@ModelAttribute User user)



//}


//    @PostMapping("/greeting")
//    public String greetingSubmit(@ModelAttribute Greeting greeting) throws IOException {
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Ignat\\Desktop\\" +
//                    greeting.getSecondName() + greeting.getFirstName() + ".ser");
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(greeting);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "result";
//    }
//
//
//    @GetMapping("/greeting/find")
//    public String greetingSubmit(@RequestParam("secondName") String secondName,
//                                 @RequestParam("firstName") String firstName, Model model)
//            throws ClassNotFoundException, IOException {
//        try {
//            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Ignat\\Desktop\\" + secondName + firstName + ".ser");
//            ObjectInputStream objectInput = new ObjectInputStream(fileInputStream);
//            Greeting greeting = (Greeting) objectInput.readObject();
//            model.addAttribute("greeting", greeting);
//        } catch (IOException | ClassNotFoundException e) {
//            return "notFound";
//        }
//        return "result";
//    }
//
//    @PostMapping("/upload")
//    public String greetingSubmit(@RequestParam("file") MultipartFile file, Model model) throws IOException, ClassNotFoundException{
//        String fileName = file.getOriginalFilename();
//        File myFile =new File("C:\\upload\\" + fileName);
//        try {
//
//    file.transferTo( myFile);
//            FileInputStream fileInputStream = new FileInputStream(myFile);
//            ObjectInputStream objectInput = new ObjectInputStream(fileInputStream);
//            Greeting greeting = (Greeting) objectInput.readObject();
//            model.addAttribute("greeting", greeting);
//        } catch (IOException | ClassNotFoundException e) {
//            return "notFound";
//        }
//        finally {
//            myFile.delete();
//        }
//        return "result";
//    }
////
//    @GetMapping("/greeting/email")
//    public String sendSimpleEmail(@RequestParam("secondName") String secondName,
//                                  @RequestParam("firstName") String firstName,
//                                  @RequestParam ("massage") String myMassage)
//            throws IOException, ClassNotFoundException {
//        try {
//            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Ignat\\Desktop\\"
//                    + secondName + firstName + ".ser");
//            ObjectInputStream objectInput = new ObjectInputStream(fileInputStream);
//            Greeting greeting = (Greeting) objectInput.readObject();
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(greeting.getEmail());
//            message.setSubject("Hello");
//            message.setText(myMassage);
//            this.emailSender.send(message);
//        } catch (IOException | ClassNotFoundException e) {
//            return "notFound";
//        }
//        return "send";
//    }

