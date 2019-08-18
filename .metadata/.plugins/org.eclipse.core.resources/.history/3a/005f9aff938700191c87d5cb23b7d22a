<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test des expressions EL</title>
    </head>
    <body>
    <p>
        <% 
        /* Création d'une Map */
        java.util.Map<String,Integer> desserts = new java.util.HashMap<String, Integer>();
        desserts.put("cookies", 8);
        desserts.put("glaces", 3);
        desserts.put("muffins", 6);
        desserts.put("tartes aux pommes", 2);
        
        request.setAttribute("desserts" , desserts);
        %>
        
        <!-- Les quatre syntaxes suivantes retournent la valeur associée à la clé "cookies" de la Map de desserts  -->
        ${ desserts.cookies }<br />
        ${ desserts.get("cookies") }<br />
        ${ desserts['cookies'] }<br />
        ${ desserts["cookies"] }<br />

        <% 
        /* Création d'une chaîne nommée "element" et contenant le mot "cookies" */
        String element = "cookies"; 
        request.setAttribute("element",element);
        %>
        <!-- Il est également possible d'utiliser un objet au lieu d'initialiser la clé souhaitée directement dans l'expression -->
        ${ desserts[element] }<br />
    </p>
    </body>
</html>