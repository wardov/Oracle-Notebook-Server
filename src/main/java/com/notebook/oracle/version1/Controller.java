package com.notebook.oracle.version1;

import com.notebook.oracle.version1.NotSupportedException;
import com.notebook.oracle.version1.ParseException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class Controller {

    @RequestMapping(method = RequestMethod.POST, value = "/execute")
    public Result execute(
            final @RequestBody Request request,
            final HttpSession session
    ) throws NotSupportedException, ParseException {

        
        Input input = new Input(request.getCode());

       
        Workflow workflow;

        if (session.getAttribute(request.getSessionId()) == null) {
            
            workflow = new Workflow();
            session.setAttribute(request.getSessionId(), workflow);

        } else {
            
            workflow = (Workflow) session.getAttribute(request.getSessionId());
        }
        return workflow.execute(input);
    }
}
