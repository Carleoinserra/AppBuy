package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
	ArrayList<Prodotto> listaP = new ArrayList<>();
	

	@GetMapping("/")
	public String getForm(Model model) {
	
		return "form";
	}

	@PostMapping("/submit")
	public String PostSubmit(Model model, @RequestParam("nome") String nome, @RequestParam("marca") String marca,
			@RequestParam("prezzo") double prezzo, @RequestParam("immagine") String url) {
		model.addAttribute("nome", nome);
		model.addAttribute("marca", marca);
		model.addAttribute("prezzo", prezzo);
		model.addAttribute("immagine", url);
		listaP.add(new Prodotto(nome, marca, prezzo, url));
		return "submit";
	}

	@PostMapping("/elenco")
	public String getElenco(Model model) {
		listaP.clear();
		Prodotto p1 = new Prodotto("tuta", "adidas", 60, "https://m.media-amazon.com/images/I/51iPWBiq1fL._AC_UL640_FMwebp_QL65_.jpg");
		Prodotto p2 = new Prodotto("tuta", "nike", 80, "https://m.media-amazon.com/images/I/71RjP6oLidL._AC_UL640_FMwebp_QL65_.jpg");
		Prodotto p3 = new Prodotto("tuta", "jordan", 130, "https://m.media-amazon.com/images/I/41j4TwlLhvL._AC_UL640_FMwebp_QL65_.jpg");
		listaP.add(p1);
		listaP.add(p2);
		listaP.add(p3);
		model.addAttribute("listaP", listaP);
		return "elenco";
	}

	@PostMapping("/elencocard")
	public String getElencoCard(Model model) {
		model.addAttribute("listaP", listaP);
		return "elencocard";
	}
	
	@PostMapping("/buy")
	public String recap(@RequestParam("selected") ArrayList<Integer> selected, Model model) {
		
		int somma = 0;
		
		for (int i = 0; i < listaP.size(); i++) {
			
			
			
			if (selected.get(i) > 0 ) {
			System.out.println("Hai acquistato " + listaP.get(i).getNome() + " marca " + listaP.get(i).getMarca()  );
			System.out.println("In " + selected.get(i) + " pezzi");
			somma += selected.get(i) * listaP.get(i).getPrezzo();
			
			
		}
		}
		
		System.out.println("La somma totale da pagare è: " + somma + " euro");
		return getElenco(model);
	}
}
