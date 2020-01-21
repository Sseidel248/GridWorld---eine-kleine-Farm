# GridWorld---eine-kleine-Farm
GridWorld - eine kleine Farm, eine Übung inzusammenhang mit dem Grid Framework

GridWorld – eine Kleine Farm

1 FarmWorld
Für die Bearbeitung dieser Hausaufgabe benötigt ihr das in der übung vorgestellte GridWorld-
Framework.
Während ihr die Funktionalität eurer eigenen Actor-Typen entwickelt, also die folgenden Aufgaben
löst, schaut immer wieder in die Quelltexte der Klassen Bug, Critter oder anderer Klassen
des Frameworks, um zu sehen welche Methoden euch schon zur Verfügung stehen. Die Klasse
Critter ist explizit dazu gedacht, sie zu erweitern: Sie ist als Oberklasse für eigene Klassen gedacht,
die etwas mit ihren Nachbarn machen. Der Standard-Critter \macht" etwas sehr spezielles
mit ihnen, er frisst sie nämlich auf. Das ist aber nur ein Beispiel. In critters ßndet ihr zum Beispiel
einen ChameleonCritter, der die Farbe eines Nachbarn annimmt.

1.1	Tier 
Programmiert einen Actor-Typen Animal. Ein Animal soll ein Attribut age haben, welches das
Alter des Tiers in Zeitschritten angibt. Sorgt dafür, dass die graphische Oberäche das Alter
anzeigt, wenn man mit der Maus draufgeht. Ansonsten soll ein Animal hauptsächlich da sein und
ungerichtet herumlaufen.
In GridWorld gibt es zwei Beispiel-Actor-Typen als Vorlage, die sich bewegen: Bug und Critter.
Eine sinnvolle Lösungsstrategie wäre, von einer der beiden das Bewegungsverhalten zu kopieren.
Dies ist tatsächlich ein Fall, in dem das Kopieren von Quellcode keine schlechte Idee ist. Es geht
darum, Musterverhalten aus anderem Code zu übernehmen, mit der Option, dies später anzupassen.
Eine dieser beiden Klassen zu erweitern wäre dagegen keine so gute Idee: Sowohl der Bug als
auch der Critter sind dafür zu speziell.
Ein Animal soll auch nicht, wie etwa der Bug, andere Dinge aus dem Weg räumen, wie der Bug
es zumindest mit Blumen macht. Es soll nur freie Felder betreten.
Füllt, analog zum BugRunner, die Klasse FarmWorldRunner, in dem ihr ein Paar Tiere in einen
Bauernhof setzt. Die ActorWorld hat auch eine Methode, mit der ein größeres Gitter gesetzt
werden kann. Nehmt ein 20*20-Gitter, oder auch ein größeres.

1.2 Ziege 
Programmiert einen Actor-Typen Goat. Eine Goat ist ein Animal. In jedem Step soll eine Goat
drei Dinge (in dieser Reihenfolge) tun:
1.)	Sie schaut mit einerWahrscheinlichkeit von 1/6 um sich (es muss sich dafür nicht extra drehen) und wenn ein Feld um sie herum frei ist, generiert sie eine weitere Goat und setzt sie dort hin.
2.)	Wenn sie älter als 15 Zeitschritte ist, stirbt sie mit einer Wahrscheinlichkeit von 1/5 und hinterlässt eine Flower. (Sie wird mit Alter 0 in Zeitschritt x geboren und kann frühestens im x+15. Zeitschritt sterben.)
3.)	Ziegen sind sehr geschickte und leidenschaftliche Kletterer. Wenn eine Ziege vor einem Felsen oder mehreren auf gerader Linie hintereinander liegenden Felsen (Rock) steht und ihren Blick (ihre Direction) auf den Fels / die Felsen gerichtet hat und das Feld hinter den Felsen frei ist (und im Grid liegt), springt sie über die Felsen auf das dahinter liegende Feld (fügen Sie einige Rocks in ihre FarmWorld ein, um dies zu testen). Dies ersetzt ggf. in diesem Zeitschritt das Bewegungsverhalten (und ggf. Drehung bzw. Blickrichtungsänderung) eines allgemeinen Animals für diese Ziege. In allen anderen Fällen bewegen sich Ziegen wie ein allgemeines Animal.

1.3 Blumenwirt 
Damit das ein halbwegs stabiles Ökosystem wird, muss jemand die Blumen einsammeln, sonst
mauern die Ziegen sich ein und sterben aus. Programmiert dazu einen Actor-Typen Farmer.
Ein Farmer ist ein Critter, der aber keine Ziegen, sondern nur (alle auf den 8 Nachbarfeldern
befindlichen) Blumen isst. Setzt einen oder zwei solche Farmer zu den Tieren dazu.

1.4 Schöpfer 
Wir wollen nicht zu viel verraten, aber das gibt schon ein recht interessantes Verhalten, auf das
man vielleicht von alleine nicht kommt. Man kann die Größe und die Anzahl der Blumenwirte
variieren und erhält unterschiedliche Muster.
Allerdings gibt es am Anfang immer eine gewisse Gefahr, dass die Ziegen aussterben. Baut
einen CreatorFarmer, der ein spezieller Farmer ist. Er sammelt keine Blumen, sondern prüft
ständig, ob die Ziegen ausgestorben sind (er hat das ganze Grid im Blick und behält die Anzahl
der Ziegen im Auge). Wenn er feststellt, dass keine mehr da sind, produziert er eine neue Ziege
(keine Zicklein) und setzt sie neben sich.

1.5 Milchbauer 
Programmiert einen GoatMilker, der ein Farmer ist, aber nicht nur Blumen isst, sondern auch alle
Ziegen auf seinen 8 benachbarten Feldern melkt. Die gemolkenen Ziegen verlieren dabei ihre Milch
(Zeitschritt x). Erst 2 Zeitschritte später ist wieder genug Milch entstanden, sodass die betroffene
Ziege wieder gemolken werden kann (Zeitschritt x+2). Der Ziege wird dazu mitgeteilt, dass sie
gemolken wurde und sie weiß, ob sie wieder gemolken werden kann. Jedes Mal, wenn er eine Ziege
gemolken hat, erhöht der GoatMilker die Milchmenge, die sich im MilkStorage befindet, um 1.
Der MilkStorage ist auch ein eigener Actor, der gar nichts tut (außer Milch zu beinhalten). Der
GoatMilker muss da auch nicht hinlaufen, er teleportiert die Milch einfach hinein, indem er eine
eigens deßnierte Methode an ihm aufruft. Der GoatMilker kennt den MilkStorage, indem er ihn
im Konstruktor übergeben bekommt.
Warum es den MilkStorage dann überhaupt geben soll? Weil er anzeigen kann, wieviel Milch
bereits gemolken wurde und wir das sehen können. Was für ein Symbol in der Oberäche angezeigt
wird, wird über eine Hilfsklasse entschieden, die wie der Actor heißt, nur um ...Display erweitert. 
Das Framework findet diese Klasse automatisch und verwendet sie. Schreibt eine Klasse
MilkStorageDisplay extends DefaultDisplay (keine weitere Funktionalität - ein leerer Klassenkörper reicht), 
um das Bildchen von der Maske durch das viel angemessenere Standardverhalten
zu ersetzen, nämlich die Textrepräsentation des MilkStorage auf das Feld zu schreiben. Sorgt
dafür, dass die Textrepräsentation (toString) des MilkStorage sein Füllstand ist (wie bei Tieren
das Alter), der Rest geht automatisch. Es soll immer nur einen MilkStorage geben können. Der
MilkStorage kann mit der public Methode resetMilkStorage() geleert (auf 0 gesetzt) werden.

1.6 Zicklein 
Damit Ziegen nicht von Geburt an ausgewachsen sind, sollen sie zunächst ein Zicklein sein. Programmiert
dazu die Klasse GoatKid, die ein Animal ist, und lasst Ziegen keine Ziegen sondern
Zicklein gebären. Zicklein können natürlich nicht gemolken werden und auch keine anderen Zicklein
gebären. Bewegen sollen sie sich aber genauso wie Ziegen. Wenn ein GoatKid das Alter von
3 (Zeitschritten) erreicht, wird es zu einer Goat und kann sofort gemolken werden. Das Alter soll
dabei normal weiterzählen. Ein GoatKid wird mit Alter 0 geboren.

1.7 Herdentrieb 
Ziegen und Zicklein sind Herdentiere. Verändert das Bewegungsverhalten der Ziegen und Zicklein
deshalb so, dass sie sich wenn möglich auf Nachbarfelder anderer Ziegen oder Zicklein bewegen.
überlegt euch, wo ihr den Code für dieses Bewegungsverhalten am besten hinzufügt, sodass er
nicht für Zicklein und Ziegen doppelt vorhanden sein muss. Der Herdentrieb hat Vorrang vor den
anderen Bewegungsmustern, d.h. Ziegen und Zicklein gehen zu ihren Artgenossen anstatt über
Rocks zu klettern oder zufällig umherzulaufen, wenn sich ein Artgenosse in der Nähe befindet.


