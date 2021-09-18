# Stundenplan-App der HTWK v1.0.0

Die Langeweile in den Semesterferien (~pause) kann einen schon überstürzen, weswegen ich eine Stundenplan-App für Android-Geräte (später eventuell iOS) umgesetzt habe. 
Die App basiert auf der [Stundenplan-Anzeige](https://www.htwk-leipzig.de/studieren/im-studium/online-services/lehrveranstaltungsplan/seminargruppenplan/) bzw. dem [Seminargruppenplan]((https://www.htwk-leipzig.de/studieren/im-studium/online-services/lehrveranstaltungsplan/seminargruppenplan/)) der HTWK.
D.h.: Aufgerufen wird jeweils der aktuelle Stundenplan, da die Einträge unmittelbar von der Stundenplan-Anzeige heruntergeladen und angzeigt werden. Werden Änderungen im Stundenplan durchgeführt, so wird beim erneuten Abruf des Stundenplans in der App stets der Aktuelle angezeigt.
Außerdem enthält die App keine Nebenkosten oder Service-Kosten, da diese vollständig, wie schon erwähnt, auf der Stundenpl-Anzeige der HTWK basiert und lediglich die Attribute extrahiert und übersichtlich dargestellt werden.

## Download 
Installiert wird die App über APK-Datei unter folgendem [Download-Link](https://github.com/stevomat/StundenplanApp_HTWK/raw/main/app/build/outputs/apk/debug/app-debug.apk). 
Herunterladen wird eine "app-dabug.apk" in eurem Downloads-Ordner des Smartphones. 

## Installation
Ein Klick auf die APK-Datei öffnet ein Installationsmenü. Da die App nicht über ein Play-Store herunterladen wurde und demnentsprechend nicht signiert ist, müsst ihr eventuell (höhstwahrscheinlich) in den Einstellungen "Unbekannte Quellen" [aktivieren](https://www.heise.de/tipps-tricks/Externe-Apps-APK-Dateien-bei-Android-installieren-so-klappt-s-3714330.html), um die Instalaltion zuzulassen. 

Außerdem kann die Installation durch Play Protect unterbrochen werden. Einfach auf "Trotzdem installieren" klicken und im weiteren Verlauf nicht hochladen!

## Inbetriebnahme
Alle eingegeben und ausgewählten Werte werden gespeichert und beim nächsten App-Start übernommen!

Bitte Seminargruppe und Planungswoche (PW) eingeben bzw. auswählen. Die Seminargruppe könnt ihr der [Stundenplan-Anzeige](https://www.htwk-leipzig.de/studieren/im-studium/online-services/lehrveranstaltungsplan/seminargruppenplan/) entnehmen. Die Schreibweise ist die gleiche wie in der [Stundenplan-Anzeige](https://www.htwk-leipzig.de/studieren/im-studium/online-services/lehrveranstaltungsplan/seminargruppenplan/). 

Für das erfolgreiche Abrufen des Stundenplans ist die Seminargruppe und die Plannungswoche (PW) anzugeben. Standardmäßig wird die Auswahl "Aktuelle Woche" ausgewählt, dabei wird, wie der Name schon sagt, die aktuelle PW übergeben. Unter der PW-Anzeige wird der Zeitraum der PW angezeigt.

Über "Abrufen" wird in den nächsten Bildschirm gewechselt und der Stundenplan vom jeweiligen Wochentag angezeigt. Der Wochentag kann, wie in der Abb. gezeigt, oben rechts ausgewählt werden. 

Wird eines der Veranstaltung angeklickt, wird eine Message mit den dazugehörigen Bemerkungen zur jeweiligen Veranstaltung am unteren Rand ausgegeben. (siehe Abb.)

<img src="https://github.com/stevomat/StundenplanApp_HTWK/blob/main/pictures/start.PNG" width="300"> <img src="https://github.com/stevomat/StundenplanApp_HTWK/blob/main/pictures/abrufen.PNG" width="300"> <img src="https://github.com/stevomat/StundenplanApp_HTWK/blob/main/pictures/auswahl2.PNG" width="300"> <img src="https://github.com/stevomat/StundenplanApp_HTWK/blob/main/pictures/bemerkungen.PNG" width="300">

## Bug-Report
Die App kann eventuell Bugs oder Darstellungsfehler enthalten. Bitte diese einfach melden!
