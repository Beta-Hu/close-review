package com.beta.closereview.mapper;

import com.beta.closereview.SuperCloseReviewTest;
import com.beta.closereview.pojo.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

class UserMapperTest extends SuperCloseReviewTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void getPassword() {
        String password = userMapper.getPassword("betahu@qq.com");
        Assert.assertEquals(password, "aaa111");
    }

    @Test
    public void getUser(){
        User user = userMapper.getUserByEmail("betahu@qq.com");
        Assert.assertEquals(user.getUsername(), "BetaHu");
        Assert.assertEquals(user.getOrganization(), "Chongqing University Of Posts and Telecommunications");
    }

    @Test
    public void getUsersByIds(){
        List<Integer> ids = new ArrayList<>();
        ids.add(2);
        ids.add(5);
        ids.add(4);
        List<User> usersByIds = userMapper.getUsersByIds(ids);
        for (User u: usersByIds)
            System.out.println(u);
    }

    @Test
    public void addUsers(){
        String[] names = new String[]{
                "SAMUEL GUERRA",
                "JOSIAH BROOKS",
                "KARA WATKINS",
                "MADELYN STEIN",
                "JULIUS MERRITT",
                "LEONA MACDONALD",
                "LEGEND MCDOWELL",
                "ADALYN WILDER",
                "MAXINE WATKINS",
                "CONOR DELEON",
                "JAYLEN VINSON",
                "AVERY FRYE",
                "AUBREE VINCENT",
                "HECTOR CANNON",
                "JAZMIN VANG",
                "LEANNA WHITEHEAD",
                "MAKENZIE MORALES",
                "KYLEIGH HINES",
                "NELLIE CLEVELAND",
                "EMMALYN BAXTER",
                "LONDON GARDNER",
                "RAELYN TAYLOR",
                "JON MORGAN",
                "BRODIE MALONE",
                "MELVIN WONG",
                "DELANEY LLOYD",
                "PRISCILLA GUY",
                "KIERAN LAMBERT",
                "LEONARD DAVENPORT",
                "DESIREE RANDOLPH",
                "SIENA PENNINGTON",
                "MELISSA CARNEY",
                "KATE BLACK",
                "BEAU GRAHAM",
                "PAULA HAYS",
                "JESSE MILLER",
                "WALTER BRADSHAW",
                "KONNOR GATES",
                "SUNNY KENNEDY",
                "ZAYDEN JENNINGS",
                "DARIUS LEONARD",
                "LAURA MOLINA",
                "JAYCEON FIELDS",
                "MARISSA ROLLINS",
                "ADRIAN WILKERSON",
                "SYDNEY RIVERS",
                "VANCE VELAZQUEZ",
                "LIANA VAUGHAN",
                "ALISON BRADLEY",
                "CARTER GARDNER",
                "LEONA REID",
                "JULIANNA DAVENPORT",
                "TRACE SAWYER",
                "TYSON BUCKNER",
                "GABRIEL DIXON",
                "MARK RODGERS",
                "NEIL FIGUEROA",
                "TIANA JONES",
                "CARLA PAUL",
                "JADA WILKERSON",
                "KEIRA FITZGERALD",
                "SCOTT BAILEY",
                "RUTH PARKER",
                "BENEDICT JOHNSTON",
                "SHAWN PRICE",
                "ADALINE SUMMERS",
                "KENZIE GRIFFITH",
                "LEAH WILKERSON",
                "PENELOPE KEITH",
                "BLAZE MAXWELL",
                "RICHARD VALENZUELA",
                "CHEYENNE ARMSTRONG",
                "LARA BARKER",
                "ELSIE GORDON",
                "BAILEY JENKINS",
                "ALAINA GRIFFITH",
                "LIAM CHAMBERS",
                "RHYS BENJAMIN",
                "CURTIS MCGUIRE",
                "SARA MIDDLETON",
                "EVERETT CAMERON",
                "KELLY STEVENS",
                "BRODIE SUTTON",
                "AUDREY DECKER",
                "JAZMINE GOOD",
                "EMELIA CHURCH",
                "NELSON QUINN",
                "AMBROSE IRWIN",
                "ALAN CALDERON",
                "ROSIE PACE",
                "BELLA WALLACE",
                "ANDREA GRAY",
                "LILIANA BARRERA",
                "CAIDEN WELLS",
                "ADDYSON PERRY",
                "BARRETT CUNNINGHAM",
                "CELIA FRANKS",
                "HALLIE KINNEY",
                "VERONICA BOLTON",
                "MALEAH CARPENTER",
                "TREASURE JACOBS",
                "JOEL COMBS",
                "ADDISON COLON",
                "JAMISON HOLT",
                "ADDYSON SWANSON",
                "STERLING VARGAS",
                "ERIK PRUITT",
                "LUCIA GARZA",
                "JOELLE MORGAN",
                "ROGER MCCARTHY",
                "BRIANNA WALKER",
                "CATHERINE PHILLIPS",
                "BENJAMIN NICHOLSON",
                "LAYTON HOBBS",
                "HENRY KENNEDY",
                "JORDAN LONG",
                "VICTORIA WINTERS",
                "BIRDIE FINLEY",
                "JEREMIAH BURT",
                "WYATT BENJAMIN",
                "THEODORA CRAWFORD",
                "VANESSA ROWLAND",
                "FRANK BUSH",
                "JAMISON MORSE",
                "JADA TERRY",
                "KASH MUELLER",
                "CHANDLER OLIVER",
                "JESSICA FITZGERALD",
                "PIPER SALAZAR",
                "MERRICK STEELE",
                "KHLOE ZIMMERMAN",
                "DELLA CHERRY",
                "CORY BUCHANAN",
                "SCARLET HARRELL",
                "STEPHEN CHANEY",
                "JOSIE JOYCE",
                "CARLA WALTER",
                "ROSEMARY GILMORE",
                "NAOMI WAGNER",
                "NANCY HARRISON",
                "HAILEY HANSEN",
                "RAY NGUYEN",
                "MEGAN LEWIS",
                "TROY FERNANDEZ",
                "BRUCE GAINES",
                "NATHAN MADDOX",
                "GRACELYN CHRISTIAN",
                "KAIDEN JOHNS",
                "BRADLEY ROMAN",
                "REAGAN FOX",
                "ARIANA ROBINSON",
                "CONNER RODRIQUEZ",
                "LIV TREVINO",
                "SIMONE CRUZ",
                "LIANA GONZALES",
                "HANK FERGUSON",
                "THEO RICHMOND",
                "COOPER SALINAS",
                "KADEN BOWMAN",
                "DONALD BONNER",
                "VIRGINIA SHEPHERD",
                "BRENNA NORTON",
                "JENSEN KIRBY",
                "WYNTER EMERSON",
                "JON GARZA",
                "ELEANORA MARSHALL",
                "MARIE GATES",
                "ANSON HARRELL",
                "EVERLY MORGAN",
                "JULIE MCGUIRE",
                "MICHEAL DILLON",
                "RONALD BELL",
                "HOPE JOHNSON",
                "SOPHIA KELLY",
                "FELICITY GARRISON",
                "GABRIELLA GUZMAN",
                "JULIE BRIGHT",
                "IRENE FOLEY",
                "KARINA SALAS",
                "LUELLA SHAFFER",
                "BRETT MCKINNEY",
                "MARTIN MEYER",
                "MALAKAI KIDD",
                "STERLING WEAVER",
                "ROYCE BENTLEY",
                "LUCAS HAHN",
                "ABEL DRAKE",
                "EVERLY ANDREWS",
                "JOHN FARLEY",
                "HUNTER RICHARDSON",
                "MELANY NEWMAN",
                "TRISTAN CROSS",
                "MONICA WYATT",
                "LINDA CASTILLO",
                "CHARLEY SAVAGE",
                "DELLA HOLT",
                "JAYCE HATFIELD",
                "AMELIA KLEIN",
                "EVERETT CRUZ",
                "GAGE CHANEY",
                "PAULA EWING",
                "HOPE MAXWELL",
                "WILLA HEBERT",
                "TUCKER WYNN",
                "ERIK BAXTER",
                "ARTHUR MCDOWELL",
                "LOLA WHITNEY",
                "AUBREE STEWART",
                "TRINITY MCDANIEL",
                "LILLIAN HUBER",
                "ARLO FRYE",
                "MELANIE CANTU",
                "TRENTON WEAVER",
                "CHANEL VAUGHAN",
                "IRIS WALTON",
                "BRANTLEY BRADFORD",
                "NICHOLAS HOLMES",
                "JEFFREY KAUFMAN",
                "PIERCE HOWELL",
                "MACY ONEIL",
                "JAX BERNARD",
                "PHOEBE STEVENS",
                "MAXWELL WATSON",
                "EDWARD BURRIS",
                "DILLON MCDONALD",
                "ABIGAIL FITZGERALD",
                "KODY KAUFMAN",
                "SAGE POWERS",
                "KAMERON KRAMER",
                "LILAH MENDOZA",
                "EMILY HALE",
                "OPHELIA SPARKS",
                "KATIE MCCRAY",
                "KIARA NEAL",
                "KATHRYN GONZALEZ",
                "DESIREE DRAKE",
                "LAINEY PATRICK",
                "VALERIE HEBERT",
                "HADLEY CALDWELL",
                "DRAKE WRIGHT",
                "EDITH OLIVER",
                "LILLIAN EVANS",
                "IRENE KIDD",
                "KENNETH ALLISON",
                "ETTA JENNINGS",
                "DAMIAN RAYMOND",
                "HUGH CARTER",
                "BRYSON WEISS",
                "KIERA GUERRERO",
                "ISAAC TORRES",
                "REBEKAH FLEMING",
                "KATHLEEN MCDONALD",
                "IRIS HARRISON",
                "RICHARD BRADLEY",
                "CARLY KOCH",
                "ANNE BAIRD",
                "ALEX FITZGERALD",
                "MAYSON KNOX",
                "WINSTON ELLISON",
                "JUSTICE KRAMER",
                "AIDAN RANDOLPH",
                "CAMDEN MCINTOSH",
                "GRACELYNN YOUNG",
                "FORREST PATRICK",
                "CULLEN SIMMONS",
                "BROOKLYNN ORR",
                "ASHTON POWELL",
                "SAMSON CASTILLO",
                "PENELOPE ODOM",
                "NICKOLAS LANDRY",
                "SIENNA BOOKER",
                "STELLA SCHROEDER",
                "DUNCAN GOODWIN",
                "WILLOW RAMSEY",
                "KARLEE BROWNING",
                "CAIRO RANDALL",
                "MARTHA AVERY",
                "CLARK BARTLETT",
                "KHLOE VELASQUEZ",
                "AARON KAUFMAN",
                "MADISON MORROW",
                "SUMMER BROWNING",
                "LILLIE CASTRO",
                "OLIVIA ACEVEDO",
                "ALEAH ESPINOZA",
                "DAMON MCCULLOUGH",
                "EMELIA WOODARD",
                "HUGO ROWLAND",
                "SYLVIA HICKS",
                "AVERY HESS",
                "MATTHEW CONRAD",
                "CHASE MORIN",
                "ZAVIER HARRISON",
                "WILLOW MACIAS",
                "LEVI WITT",
                "CLEO COTTON",
                "CODY BARLOW",
                "MADELYN SCHNEIDER",
                "DESTINY WATKINS",
                "JOEL BEARD",
                "CULLEN GRANT",
                "JAXX TYLER",
                "BRAYDEN PITTS",
                "RYANN BONNER",
                "CELIA DORSEY",
                "ADALINE GROSS",
                "ZOIE BRYANT",
                "MYA EMERSON",
                "TYLER GENTRY",
                "JEFFREY STONE",
                "MIRACLE WAGNER",
                "SABRINA AVILA",
                "SUNNY MCCONNELL",
                "RYLEIGH MENDOZA",
                "DANE WARE",
                "ISABEL ORTIZ",
                "PRESLEY GOFF",
                "BRENDEN BEST",
                "REUBEN WOLFE",
                "ANDREW ROMAN",
                "MAVIS VAZQUEZ",
                "ANNALISE RAMSEY",
                "ADRIANNA KELLY",
                "MAYSON GOFF",
                "ROSALYN EMERSON",
                "DARIAN PERRY",
                "ALEXIA STOKES",
                "SLOANE FLORES",
                "MIKE HARRISON",
                "JOYCE AGUILAR",
                "EMORY DOUGLAS",
                "LOUISE PUCKETT",
                "CADE RILEY",
                "AUSTIN DAVIS",
                "GREYSON MIDDLETON",
                "LEROY KENT",
                "RYLEE SCHULTZ",
                "IVY WOLFE",
                "DAMON OCHOA",
                "DESTINY BURNETT",
                "DELILAH BARKER",
                "NOLAN BRADLEY",
                "BRODIE EDWARDS",
                "CHARLEE DAY",
                "BENJAMIN HAWKINS",
                "SUSAN CHAMBERS",
                "EMMANUEL HIGGINS",
                "DALTON STRONG",
                "MELISSA STAFFORD",
                "JACE MAYS",
                "ANNABELLE WADE",
                "MORGAN RASMUSSEN",
                "ETTA HUMPHREY",
                "RAYMOND DALE",
                "LILIANA BRADFORD",
                "ANGIE BARRY",
                "JAYDE BARRY",
                "JOE MCDONALD",
                "HATTIE STAFFORD",
                "JEREMY WEST",
                "AUBRIE FERRELL",
                "DELLA VARGAS",
                "JERICHO PEREZ",
                "TUCKER FOSTER",
                "KATHERINE STRONG",
                "HEATH LINDSEY",
                "ROGER RODRIQUEZ",
                "DENVER VAUGHN",
                "AMELIA JAMES",
                "EMMALYN WHEELER",
                "KINGSLEY BARRETT",
                "KYLIE HERNANDEZ",
                "BETHANY ELLIS",
                "LAURA BUCKNER",
                "KATHLEEN LANDRY",
                "MARLOWE HARRINGTON",
                "AMANDA ALLISON",
                "RIVER HESS",
                "JAYLEE VAZQUEZ",
                "ROBIN RIVAS",
                "AMBER VINCENT",
                "AUGUST VELEZ",
                "JOANNA MCCORMICK",
                "XANDER PRATT",
                "SIENNA KOCH",
                "FOREST BUCKNER",
                "MESSIAH QUINN",
                "IAN MCINTYRE",
                "AUBREE NEWTON",
                "JULIANNA BERRY",
                "LIANA VELASQUEZ",
                "PAYTON WILCOX",
                "DELILAH ROWLAND",
                "LEE WEAVER",
                "ANDY YATES",
                "JAIME BRYAN",
                "MICHELLE WOODS",
                "DORIAN HURST",
                "GEORGIA WEBER",
                "DOMINIC SLATER",
                "JOELLE COFFEY",
                "CHAD SPENCER",
                "KARSON BAKER",
                "JOSEPH SCHNEIDER",
                "CALLIE WISE",
                "HEATH MORTON",
                "REGINALD RIVAS",
                "MARGO SULLIVAN",
                "JAX FLOWERS",
                "DUSTIN KEY",
                "KYLA COMBS",
                "LEVI DODSON",
                "JORDYN BOWERS",
                "LIANA FARMER",
                "LOGAN CASTANEDA",
                "WILL STANLEY",
                "SOLOMON POTTER",
                "OPAL BRIDGES",
                "CHARLOTTE GREENE",
                "SETH HINTON",
                "RYAN LAWRENCE",
                "SKY FLEMING",
                "LEYLA BASS",
                "FLORA MANNING",
                "DORIAN CORTEZ",
                "ZACHARIAH GOODMAN",
                "BRANTLEY GUTHRIE",
                "ISABEL FINCH",
                "KNOX RODGERS",
                "SULLIVAN HORNE",
                "BROOKLYNN HAMPTON",
                "AURORA PARKS",
                "VALERIE GENTRY",
                "LUCIAN KING",
                "RHYS GOMEZ",
                "AZALEA ROY",
                "GISELLE HOLLAND",
                "ROSIE GAMBLE",
                "AILEEN FOREMAN",
                "JEFFERSON BOLTON",
                "KASON ROBERTSON",
                "RACHEL FOWLER",
                "JORDYN DOTSON",
                "LILIANA HOLDER",
                "RUDY COLEMAN",
                "MADISON GOFF",
                "KAYLEE ELLIS",
                "HAZEL FRANCIS",
                "REED TRAVIS",
                "CHARLEE BECK",
                "LIZBETH HANSEN",
                "LAURYN NELSON",
                "CHRISTIAN BARRY",
                "LEONARD PRICE",
                "BRAXTON DANIEL",
                "JEREMIAH TERRELL",
                "ROSALYN WYATT",
                "KOLE THORNTON",
                "AUTUMN LEBLANC",
                "ALEC NAVARRO",
                "TUCKER YOUNG",
                "LESLIE WALLACE",
                "CYNTHIA BENTON",
                "MILEY BRIDGES",
                "NELLIE HORTON",
                "REESE ALVARADO",
                "LAYLA PENNINGTON",
                "MAXWELL HARTMAN",
                "LYDIA BARBER",
                "FRANCES GOODMAN",
                "GREY HORNE",
                "CASEY SEXTON",
                "MAXINE NICHOLS",
                "BENSON FLETCHER",
                "GISELLE CAMERON",
                "PHILLIP ODOM",
                "MALAKAI GUY",
                "KONNOR BLACKWELL",
                "MAKENNA BRIGHT",
                "SULLIVAN BENNETT",
                "CHRISTINE FARRELL",
                "CASON LANDRY",
                "AMBER MCKENZIE",
                "RYKER DRAKE",
                "JESSE SAMPSON",
                "BRIAN BENNETT",
                "JOLENE OLIVER",
                "HAYLEY PARRISH",
                "SARAH GAMBLE",
                "REBEKAH TOWNSEND",
                "SLOAN MEYER",
                "JASE GILL",
                "IAN PRICE",
                "ODIN AUSTIN",
                "JAIME BRENNAN",
                "MAGNOLIA FUENTES",
                "JAYLA WOODWARD",
                "JAMISON WRIGHT",
                "LILLY NORTON",
                "MIA WHITEHEAD",
        };

        String[] organizations = new String[]{
                "Hubei Institute of Life Sciences",
                "South China Institute of Ecology",
                "South China Medical Research Institute",
                "China College of Traditional Chinese Medicine",
                "Giant Hard Research Institute",
                "Peanut industrial park",
                "Shaanxi Agricultural College",
                "Chongqing University of Posts and Telecommunications",
                "Beichuan University",
                "University of California",
                "Royal Electrotechnical College",
                "Libyan University of Automobile",
                "Agricultural University of Zambia",
                "The Brazilian Medical School",
                "Guangdong Computer University",
                "Old Occidental College",
                "American Free Shooting Academy",
                "Western School of Management"
        };

        String email;
        StringBuilder domain;
        String organization;
        User user;
        for (String name: names){
            name = name.toLowerCase();

            email = name.replace(" ", ".");

            organization = organizations[(int) (organizations.length * Math.random())];
            domain = new StringBuilder();
            domain.append(organization.charAt(0));
            for (int i = 1; i < organization.length(); i++){
                if (organization.charAt(i - 1) == ' '){
                    domain.append(organization.charAt(i));
                }
            }

            int locationSpace = name.lastIndexOf(' ');
            name = name.substring(0, 1).toUpperCase() + name.substring(1, locationSpace + 1) + name.substring(locationSpace + 1, locationSpace + 2).toUpperCase() + name.substring(locationSpace + 2);

            email = email + "@" + domain.toString().toLowerCase() + ".com";

            user = new User();
            user.setUsername(name);
            user.setPassword(name);
            user.setOrganization(organization);
            user.setEmail(email);

            userMapper.insertSelective(user);
        }
    }
}