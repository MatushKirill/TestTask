package kirill.controller;

import kirill.model.DatabaseProperties;
import kirill.model.TableInfo;
import kirill.service.TablesService;
import kirill.validation.DbValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by kirill on 24/10/16.
 */
@Controller
@SessionAttributes("tables")
public class DateBaseController {
    @Autowired
    TablesService tablesService;
    @Autowired
    DbValidator dbValidator;


    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String enterProp(Model model){

        model.addAttribute("dbProp",new DatabaseProperties());
        return "welcomePage";
    }

    @RequestMapping(value = "/welcome",method = RequestMethod.POST)
    public String getDb(@Valid @ModelAttribute("dbProp") DatabaseProperties dateBaseProperties, BindingResult result, Model model
                        ){
        tablesService .refreshDatabase(dateBaseProperties);
        dbValidator.validate(dateBaseProperties,result);
        if (result.hasErrors()){
            return "welcomePage";
        }
        List<TableInfo> tables= tablesService.getTable(dateBaseProperties);
        model.addAttribute("tables",tables);
        return "redirect:dbPage";
    }
    @RequestMapping(value = "/dbPage",method = RequestMethod.GET)
    public String showTables(){

        return "dbPage";
    }


}
