package com.notebook.oracle.version1;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;



public class Workflow {
    
    private Context context;

    private String output = "";

    private Boolean isOpen = false;

    public Workflow() {
        
        open();
    }

    private void open() {
        
        context = Context.newBuilder().out(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                output += (char) b;
            }
        }).allowAllAccess(true).build();

        isOpen = true;
    }


    public Result execute(Input input) {
      

        if (!isOpen) {
           
            open();
        }

  
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isOpen = false;
                context.close(true);
            }
        }, 10000);

        Value value = context.eval(input.getLanguage(), input.getCode());
        timer.cancel();

        String result = getResult();

        if (result == "" && value != null && !value.isNull()) {
            
            result = value.toString();
        }

        return new Result(result);
    }

    private String getResult() {
        String result = output.replace("\n", "");
        output = "";
        return result;
    }

}
