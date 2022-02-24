package com.example.fabriquetesttask.controller.urls;


public interface Urls {
    String ROOT = "api";

    interface Survey {
        String NAME = "survey";
        String FULL = ROOT + "/" + NAME;

        interface id{
            String NAME = "{id}";
            String FULL = Survey.FULL + "/" + NAME;
        }
        interface question{
            String NAME = "question";
            String FULL = Survey.FULL + "/" + NAME;
            interface id{
                String NAME = "{id}";
                String FULL = question.FULL + "/" + NAME;
            }
        }
        interface pass{
            String NAME = "pass";
            String FULL = Survey.FULL + "/" + NAME;
            interface id{
                String NAME = "{id}";
                String FULL = pass.FULL + "/" + NAME;
            }
        }
        interface details{
            String NAME = "details";
            String FULL = Survey.FULL + "/" + NAME;
            interface id{
                String NAME = "{id}";
                String FULL = details.FULL + "/" + NAME;
            }
        }
    }
    interface Auth {
        String NAME = "auth";
        String FULL = ROOT + "/" + NAME;

        interface login{
            String NAME = "login";
            String FULL =  Auth.FULL + "/" + NAME;
        }
        interface logout{
            String NAME = "logout";
            String FULL =  Auth.FULL + "/" + NAME;
        }
    }
}
