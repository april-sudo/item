package carrotproject.infra;
import carrotproject.domain.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

 @RestController
 @RequestMapping(value="/items")
 @Transactional
 public class ItemController {
        @Autowired
        ItemRepository itemRepository;

        @RequestMapping(value="/{itemId}", method = RequestMethod.GET)
        public Item getItem(@PathVariable("itemId") Long itemId) {
            return itemRepository.findById(itemId).orElse(null);
        }
        
        @RequestMapping(value="/{itemId}", method = RequestMethod.DELETE)
        public void deleteItem(@PathVariable("itemId") Long itemId) {
            itemRepository.deleteById(itemId);
        }

        @GetMapping("/callMemleak")
        public void callMemleak() {
            try {
                this.memLeak();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    
        public void memLeak() throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
            Class unsafeClass = Class.forName("sun.misc.Unsafe");
            Field f = unsafeClass.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            System.out.print("4..3..2..1...");
            try{
                for(;;)
                unsafe.allocateMemory(1024*1024);
            } catch(Error e) {
                System.out.println("Boom :)");
                e.printStackTrace();
            }
        }
 }
