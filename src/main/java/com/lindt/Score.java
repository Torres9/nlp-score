package com.lindt;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Score {
    public static void main(String args[]) throws Exception {
        if(args.length < 3) {
            System.out.println("Usage: Score <gmail-account> <gmail-passwd> <path-to-upload-file>");
            System.exit(-1);
        }
        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        final WebClient webClient = new WebClient();
        HtmlPage page = webClient.getPage("http://sfu-nlp-class.appspot.com/");
        List<HtmlForm> forms = page.getForms();
        HtmlForm logIn = null;
        for (int i = 0; i < forms.size(); i++) {
            if (forms.get(i).getId().equals("gaia_loginform")) {
                logIn = forms.get(i);
                break;
            }
        }
        if (logIn == null) {
            System.out.println("Couldn't find log in form, maybe the html page is changed");
            System.exit(-1);
        }
        HtmlInput email = logIn.getInputByName("Email");
        HtmlInput passwd = logIn.getInputByName("Passwd");
        HtmlInput submit = logIn.getInputByName("signIn");
        email.setValueAttribute(args[0]);
        passwd.setValueAttribute(args[1]);
        HtmlPage confirm = submit.click();
        HtmlElement confirmButton = (HtmlElement) (confirm.getElementById("approve_button"));
        HtmlPage uploadPage = confirmButton.click();
        forms = uploadPage.getForms();
        HtmlForm uploadForm = null;
        for (int i = 0; i < forms.size(); i++) {
            if (forms.get(i).hasAttribute("enctype")) {
                uploadForm = forms.get(i);
                break;
            }
        }
        if (uploadForm == null) {
            System.out.println("Couldn't find upload form, maybe the html page is changed");
            System.exit(-1);
        }
        HtmlInput file = uploadForm.getInputByName("file");
        file.setValueAttribute(args[2]);
        HtmlButton uploadButton = (HtmlButton) (uploadPage.getElementById("upload-2"));
        uploadButton.removeAttribute("disabled");
        HtmlPage result = uploadButton.click();
        List<DomElement> tables = result.getElementsByTagName("table");
        HtmlTable hwTable = null;
        for (int i = 0; i < tables.size(); i++) {
            if (tables.get(i).getElementsByTagName("thead").get(0).getElementsByTagName("tr").get(0)
                    .getElementsByTagName("th").get(0).asText().equals("No.")) {
                hwTable = (HtmlTable) (tables.get(i));
                break;
            }
        }
        if (hwTable == null) {
            System.out.println("Couldn't find table, maybe the html page is changed");
            System.exit(-1);
        }
        float score = Float.valueOf(hwTable.getElementsByTagName("tbody").get(0).getElementsByTagName("tr").get(0)
                .getElementsByTagName("td").get(3).asText());
        System.out.println("Score : " + String.valueOf(score));
        webClient.closeAllWindows();
    }
}
