package kirill.controller;

import kirill.dao.DbDao;
import kirill.model.DatabaseProperties;
import kirill.model.TableInfo;
import kirill.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;

/**
 * Created by kirill on 24/10/16.
 */
@Controller
@SessionAttributes("tables")
public class DateBaseController {
    @Autowired
    DbDao dbDao;
    @Autowired
    TablesService tablesService;


    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String enterProp(Model model){

        model.addAttribute("dbProp",new DatabaseProperties());
        return "welcomePage";
    }

    @RequestMapping(value = "/welcome",method = RequestMethod.POST)
    public String getDb(@ModelAttribute("dbProp") DatabaseProperties dateBaseProperties, BindingResult result, Model model
                        ){
        if (result.hasErrors()){
            return "welcomePage";
        }
        tablesService .refreshDatabase(dateBaseProperties);
        List<TableInfo> tables= tablesService.createTable(dateBaseProperties);
        model.addAttribute("tables",tables);
        return "redirect:dbPage";
    }
    @RequestMapping(value = "/dbPage",method = RequestMethod.GET)
    public String showTables(){
        
        return "dbPage";
    }


}
