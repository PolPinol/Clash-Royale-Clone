Drop database if exists AgeRoyale;
Create database AgeRoyale;
Use AgeRoyale;

Drop table if exists User;
Create table User(
    id integer auto_increment,
    name varchar(255),
    password varchar(255),
    email varchar(255),
    points integer,
    numPlays integer,
    Primary Key (id)
);

Drop table if exists Troop;
Create table Troop(
    id integer auto_increment,
    price integer,
    name varchar(255),
    life integer,
    type integer,
    urlCardDefault varchar(255),
    urlCardSelected varchar(255),
    urlTroop varchar(255),
    urlTroopIA varchar(255),
    attack integer,
    Primary Key (id)
);

Drop Table if exists Record;
Create table Record(
    id integer auto_increment,
    name varchar(255),
    date varchar(255),
    hour varchar(255),
    winner int,
    userName varchar(255),
    Primary Key(id)
);

Drop Table if exists RecordData;
Create table RecordData(
    recordName varchar(255),
    rowPos int,
    columnPos int,
    troopName varchar(255),
    isUser int,
    millis long
);

INSERT INTO Troop ( price, name, life, type, urlCardDefault, urlCardSelected, urlTroop, urlTroopIA, attack )
VALUES (5, "Mago", 15, 0, "img/magocard.png", "img/magocardselected.png", "img/mago_moviment1.gif", "img/mago_ia.png", 12);

INSERT INTO Troop ( price, name, life, type, urlCardDefault, urlCardSelected, urlTroop, urlTroopIA, attack )
VALUES (3, "Arquera", 10, 0, "img/arqueracard.png", "img/arqueracardselected.png", "img/arquera1.gif", "img/arquera2.gif", 7);

INSERT INTO Troop ( price, name, life, type, urlCardDefault, urlCardSelected, urlTroop, urlTroopIA, attack )
VALUES (3, "Caballero", 20, 0, "img/caballerocard.png", "img/caballerocardselected.png", "img/caballero1.gif", "img/caballero2.gif", 5);

INSERT INTO Troop ( price, name, life, type, urlCardDefault, urlCardSelected, urlTroop, urlTroopIA, attack )
VALUES (5, "Torre", 40, 1, "img/torrecard.png", "img/torrecardselected.png", "img/torre_moviment1.gif", "img/torre_ia.png", 4);

INSERT INTO Troop ( price, name, life, type, urlCardDefault, urlCardSelected, urlTroop, urlTroopIA, attack )
VALUES (1, "Hielo", 15, 1, "img/hielocard.png", "img/hielocardselected.png", "img/hielo1.gif", "img/hielo2.gif", 2);

INSERT INTO Troop ( price, name, life, type, urlCardDefault, urlCardSelected, urlTroop, urlTroopIA, attack )
VALUES (8, "Golem", 60, 1, "img/golemcard.png", "img/golemcardselected.png", "img/roca1.gif", "img/roca2.gif", 7);