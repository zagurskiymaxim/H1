package com.joreijarr.studycontrol.controllers;

import com.joreijarr.studycontrol.models.Notes;
import com.joreijarr.studycontrol.models.Clients;
import com.joreijarr.studycontrol.containers.notesContainer;
import com.joreijarr.studycontrol.models.Tours;
import com.joreijarr.studycontrol.models.Users;
import com.joreijarr.studycontrol.repo.ToursRepository;
import com.joreijarr.studycontrol.repo.ClientsRepository;
import com.joreijarr.studycontrol.repo.NoteRepository;
import com.joreijarr.studycontrol.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class startController {


    public Boolean authorized = false;
    public Users current_user;
    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    public NoteRepository noteRepository;

    @Autowired
    public ToursRepository toursRepository;

    @Autowired
    public ClientsRepository clientsRepository;


    @GetMapping("/exit")
    public String exit(Model model)
    {
        current_user = null;
        authorized = false;
        return "redirect:/";
    }


    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("title", "Головна");
        if(authorized)
        {
            model.addAttribute("fullname", current_user.getUser_fullname());
                return "index_user";
        }
        else
        {
            return "redirect:/authorize";
        }

    }


    @GetMapping("/authorize")
    public String start(Model model) {

        model.addAttribute("title", "Авторизація");
        if(!authorized)
        {
            return "authorize";
        }
        else
        {
            return "redirect:/";
        }
    }


    @PostMapping("/authorize")
    public String authorize(Model model, @RequestParam String user_login, @RequestParam String user_password)
    {
        Iterable<Users> users_it = usersRepository.findAll();
        List<Users> users = new ArrayList<>();
        users_it.forEach(users::add);
        for (int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUser_login().equals(user_login) && users.get(i).getUser_password().equals(user_password))
            {
                authorized = true;
                current_user = users.get(i);
                break;
            }
        }
        return "redirect:/";
    }





    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("title", "Реєстрація");
        if(!authorized)
        {
            return "registration";
        }
        else
        {
            return "redirect:/";
        }
    }


    @PostMapping("/registration")
    public String registration(Model model, @RequestParam String user_login, @RequestParam String user_password,
                               @RequestParam String user_fullname)
    {
        Users new_user = new Users(user_login, user_password, user_fullname);
        usersRepository.save(new_user);
        return "redirect:/authorize";
    }





    @GetMapping("/notes")
    public String mans(Model model) {

        model.addAttribute("title", "Записи");

        Iterable<Tours> tours_it = toursRepository.findAll();
        List<Tours> tours = new ArrayList<>();
        tours_it.forEach(tours::add);

        Iterable<Clients> clients_it = clientsRepository.findAll();
        List<Clients> clients = new ArrayList<>();
        clients_it.forEach(clients::add);

            Iterable<Notes> notes_it = noteRepository.findAll();
            List<Notes> notes = new ArrayList<>();
            notes_it.forEach(notes::add);

            List<notesContainer> cnt = new ArrayList<>();
            for(int i = 0; i < notes.size(); i++)
            {
                notesContainer tmp = new notesContainer();
                tmp.note_date = notes.get(i).note_date;
                for(int j = 0; j < tours.size(); j++)
                {
                    if(Math.toIntExact(tours.get(j).getTour_id()) == Integer.parseInt(notes.get(i).getTour()))
                    {
                        tmp.tour = tours.get(j).getName();
                        break;
                    }
                }


                for(int j = 0; j < clients.size(); j++)
                {
                    if(Math.toIntExact(clients.get(j).getClient_id()) == Integer.parseInt(notes.get(i).getClient()))
                    {
                        tmp.client = clients.get(j).getFull_name();
                        break;
                    }
                }

                cnt.add(tmp);
            }


            model.addAttribute("notes", cnt);
            return "notes_page";
    }

    @GetMapping("/notes/add")
    public String notesAdd(Model model) {
        Iterable<Tours> tours_it = toursRepository.findAll();
        List<Tours> tours = new ArrayList<>();
        tours_it.forEach(tours::add);

        Iterable<Clients> clients_it = clientsRepository.findAll();
        List<Clients> clients = new ArrayList<>();
        clients_it.forEach(clients::add);
        model.addAttribute("clients", clients);
        model.addAttribute("tours", tours);
        model.addAttribute("title", "Новий запис");
            return "notes_add";
    }

    @PostMapping("/notes/add")
    public String notesAdd_Post(Model model, @RequestParam String client_id,
                               @RequestParam String tour_id,
                               @RequestParam String note_date) {
        Notes note = new Notes(client_id, tour_id, note_date);
        noteRepository.save(note);
        return "redirect:/notes";
    }




    @GetMapping("/tours")
    public String products(Model model) {

        model.addAttribute("title", "Тури");

            Iterable<Tours> products_it = toursRepository.findAll();
            List<Tours> products = new ArrayList<>();
            products_it.forEach(products::add);
            model.addAttribute("tours", products);
            return "tours_page";
    }


    @GetMapping("/tours/add")
    public String productsAdd(Model model) {
        model.addAttribute("title", "Новий тур");
        return "tours_add";
    }


    @PostMapping("/tours/add")
    public String productsAdd_Post(Model model, @RequestParam String name,
                                 @RequestParam String description,
                                 @RequestParam String price,
                                   @RequestParam String country,
                                   @RequestParam String hotel,
                                   @RequestParam String transport,
                                   @RequestParam String date_start) {
        Tours tours = new Tours(name, description, price, country, hotel,transport,date_start);
        toursRepository.save(tours);
        return "redirect:/tours";
    }



    @GetMapping("/clients")
    public String clients(Model model) {

        model.addAttribute("title", "Клієнти");
        Iterable<Clients> clients_it = clientsRepository.findAll();
        List<Clients> clients = new ArrayList<>();
        clients_it.forEach(clients::add);
        model.addAttribute("clients", clients);
        return "clients_page";
    }


    @GetMapping("/clients/add")
    public String pairsAdd(Model model) {
        model.addAttribute("title", "Новий клієнт");
        return "clients_add";
    }


    @PostMapping("/clients/add")
    public String pairsAdd_Post(Model model, @RequestParam String full_name,
                                @RequestParam String dob,
                                @RequestParam String email,
                                @RequestParam String phone) {
        Clients pair = new Clients(full_name, dob, email, phone);
        clientsRepository.save(pair);
        return "redirect:/clients";
    }

















}