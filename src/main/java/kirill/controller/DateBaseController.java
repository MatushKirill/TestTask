package kirill.controller;

import kirill.dao.DbDao;
import kirill.model.DateBaseProperties;
import kirill.model.TablesAndColums;
import kirill.service.CreateTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by kirill on 24/10/16.
 */
@Controller
public class DateBaseController {
    @Autowired
    DbDao dbDao;
    @Autowired
    CreateTables createTables;


    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String enterProp(Model model){

        model.addAttribute("dbProp",new DateBaseProperties());
        return "welcomePage";
    }

    @RequestMapping(value = "/welcome",method = RequestMethod.POST)
    public String getDb(@ModelAttribute("dbProp") DateBaseProperties dateBaseProperties, BindingResult result, Model model
                        , HttpServletRequest request){
        dbDao.setDataSource(dateBaseProperties);
        List<String> tablesNames=dbDao.getTables(dateBaseProperties.getDbName());
        Map<String,List<String>> tables=createTables.create(tablesNames,dateBaseProperties.getDbName());
        request.setAttribute("tables",tables);
        System.out.println(tables);
        return "redirect:dbPage";
    }


}
