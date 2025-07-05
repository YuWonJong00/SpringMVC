package hello.itemservice.web.item.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId")Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }
    //@PostMapping("/add")
    public String addItemV1(@RequestParam("itemName")String itemName,
                            @RequestParam("price")int price,
                            @RequestParam("quantity")Integer quantity,
                            Model model){
        Item item=new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);
        return "basic/item";
    }
    // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item")Item item
    ){

        itemRepository.save(item);

        // model.addAttribute("item", item); //modelattribute가 자동으로 넣어줌 이름은 파라미터에 있는 이름으로 똑같이 넣어줌
        return "basic/item";
    }
    //  @PostMapping("/add")
    public String addItemV3(@ModelAttribute()Item item,Model model
    ){
        //ModelAttribute의 이름 ""을 안 넣어주면 클래스 이름의 첫글자를 소문자로 바꾼 후 이를 이름으로 사용 Item -> item
        itemRepository.save(item);


        return "basic/item";
    }
    // @PostMapping("/add")
    public String addItemV4(Item item){

        itemRepository.save(item);

        // model.addAttribute("item", item); //modelattribute가 자동으로 넣어줌 이름은 파라미터에 있는 이름으로 똑같이 넣어줌
        return "basic/item";
    }
    public String addItemV5(Item item){

        itemRepository.save(item);

        return "redirection:basic/items" + item.getId();
    }
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }
}
