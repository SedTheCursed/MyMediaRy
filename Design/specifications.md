# Expression des besoins : MyMediaRy

### Sommaire

+ <a href="#infos">I. Informations générales</a> 
+ <a href="#projet">II. Présentation du projet</a>
  + <a href="#contexte">A. Contexte et objectifs</a>
  + <a href="#description">B. Description</a>
  + <a href="#cibles">C. Cibles</a>
  + <a href="#success">D. Critères de succès</a>
  + <a href="#role">E. Rôle et degrés de liberté</a>
+ <a href="#organisation">III. Organisation – Interconnexions</a>
+ <a href="#strategy">IV. Lien avec la stratégie</a>
+ <a href="#budget">V. Budget</a>
+ <a href="#delay">VI. Délais - contraintes planning</a>
+ <a href="#risques">VII. Risques</a>

<h2 id="infos">I. Informations générales</h2>

> *I need an app to manage my media library.*

Le projet répondant au besoin précédent, il a été choisi de le nommer **MyMediaRy**, contraction de l'objet qu'il gère.

Le client est Sed the Cursed, qui tiendra également le rôle de développeur.

<h2 id="projet">II. Présentation du projet</h2>

<h3 id="contexte">A. Contexte et objectifs</h3>

L'objectif du projet est la gestion de la médiathèque personnelle d'un utilisateur. Ce qui passe par quatre axes :
- Connaitre son contenu, ce qui inclut plusieurs supports, tels que livres (physiques ou numériques), musiques,
jeux video, jeux de société, ce qui peut représenter plusieurs milliers d'items.
- Connaître l'emplacement physique de chaque item.
- Prévoir son évolution à l'aide d'une liste souhait.

Un objectif secondaire est d'inclure un sélecteur d'activité, qui, selon les informations fournies, comme le type
d'activité ou le temps disponible, peut determiner ce que l'on pourrait faire lors d'une plage de temps libre, comme
quel livre lire, à quel jeu jouer ou quelle langue étudier.

S'il existe des applications de gestion de bibliothèque personnelles, elles ne couvrent qu'un support : livres, CDs,
jeux video. Et les applications de médiathèques sont destinée à des organisations. MyMediaRy marque sa singularité en
étant une application de gestion multisupport à destination des individus.  

<h3 id="description">B. Description</h3>

L'application est tout d'abord destinée à une utilisation sur appareil Android (*version 1.0*) avant d'évoluer vers 
le multiplateforme (*version 2.0*).

MyMediaRy doit avoir les fonctionnalités suivantes (avec les questions qu'elles soulèvent) :
- La capacité d'ajout/modification/suppression d'items
  - Remplissage uniquement manuel ou possibilités d'utiliser des ressources externes pour aider.
  - Liste des types de média fixe ou possibilité pour l'utilisateur d'ajouter des types.
- Recherche et consultation des items
- Mise en place d'une wishlist.
  - Doit-elle être simplement descriptive ou peut-elle contenir des liens pour acheter un item en ligne et 
auquel cas est-ce que la bibliothèque est-elle mise à jour automatiquement.
  - Possibilité d'exporter la liste pour la partager (Liste de Noël, etc.)
- La sous-application "Choice"
  - Quels sont les paramètres à prendre en compte pour que l'application puisse donner une réponse.
- Un processus de profil.
  - Nécessité d'authentification ?

<h3 id="cibles">C. Cibles</h3>

Le public visé par ce projet sont les possesseurs de contenu culturel sur divers supports (livres, CD, DVD,
numériques, etc.), qui desire ce qui est en leur possession et leur localisation. De plus, l'aspect multimedia peut
attirer les personnes qui desire synchroniser leurs activités, comme écouter de la musique steampunk tout en lisant
un livre du même genre. 

La partie "Choice" sert aux utilisateurs qui peuvent être paralysés face à un choix avec trop options et combattre
ainsi l'impression de "n'avoir rien à (lire/jouer/écouter)"

<h3 id="success">D. Critères de succès</h3>

Les critères de succès sont d’avoir une application sécurisée et robuste pouvant être déployée sur le Google Play
store.

<h3 id="role">E. Rôle et degrés de liberté</h3>

Le client et le développeur étant la même personne, il créera l'application comme il lui semblera le mieux.

<h2 id="organisation">III. Organisation – Interconnexions</h2>

**Systèmes de Gestion des Bibliothèques :**  
L'application doit être en mesure de récupérer des informations sur les items possédés,
telles que leur titre, leurs créateurs, leur support, leurs thèmes, etc.

**Système de Gestion des Wishlist :**  
Pour assurer la cohérence des informations, l'application doit intégrer les données relatives aux listes de vœux, 
telles que les items souhaités, où les acheter, leur prix, etc.

**Système de Gestion des Options de Choice :**  
Pour permettre la sous-application de trouver une réponse, l'application doit connaître les options parmi lesquelles
elle peut choisir, avec leur type, leur titre, etc.

**Système de Gestion des Utilisateurs :**  
Pour permettre l'authentification des possesseurs de bibliothèque, l'application doit interagir avec un système
de gestion des utilisateurs pour vérifier les identités et les autorisations.

*Structure des Données :*  
Les données pertinentes pour l'application comprennent les profils des possesseurs de bibliothèque, les bibliothèques,
les wishlists, les options pour Choices, etc.  
Ces données sont structurées de manière à permettre une recherche et une consultation efficaces pour les
utilisateurs de l'application.

*Intégration dans l'Environnement :*  
L'application est intégrée dans l'environnement des utilisateurs en tant qu'outil de gestion de leur contenu culturel.  
Elle fournit une interface conviviale facilitant ainsi la navigation et l'utilisation de ses fonctionnalités.

*Authentification des Utilisateurs :*  
Les utilisateurs s'authentifient via des noms de profil, crée en utilisant des identifiants uniques (par exemple,
adresses e-mail et mots de passe).  
Cette authentification garantit la sécurité et la confidentialité des données des utilisateurs.

Chaque poste est sous la responsabilité du développeur.

<h2 id="strategy">IV. Lien avec la stratégie</h2>

S’il semble similaire à des applications de ce genre, le projet se démarque par son focus mis sur la gestion de
bibliothèque multimédia à l'intention des personnes.

<h2 id="budget">V. Budget</h2>

Le projet étant un projet personnel, pas de budget a été mis en place.

<h2 id="delay">VI. Délais - contraintes planning</h2>

Aucune date-butoir n’a été fixée pour la completion du projet.  
Cependant, il serait souhaitable qu'il ait atteint certaines étapes aux dates suivantes : 
- **mi-novembre 2024 :** Wishlist finie
- **Janvier 2025 :** Commencer la version 2.0

<h2 id="risques">VII. Risques</h2>

<table>
<thead>
<tr>
<th>Risque</th>
<th>Description</th>
<th>Probabilité</th>
<th>Gravité</th>
<th>Solution</th>
</tr>
</thead>
<tbody>
<tr>
<td>Problèmes de sécurité des données</td>
<td>Fuite de données personnelles des utilisateurs, violation de la vie privée, attaques de pirates informatiques</td>
<td>4/5</td>
<td>5/5</td>
<td>Renforcer les mesures de sécurité, cryptage des données, audits de sécurité réguliers</td>
</tr>
<tr>
<td>Problèmes de compatibilité avec les appareils</td>
<td>Dysfonctionnements sur certains types d'appareils mobiles, problèmes de performance, bugs</td>
<td>2/5</td>
<td>3/5</td>
<td>Tests de compatibilité approfondis sur une large gamme d'appareils, mises à jour régulières</td>
</tr>
<tr>
<td>Défaillance du système de gestion des utilisateurs</td>
<td>Problèmes d'authentification, accès non autorisés, perte d'informations utilisateurs</td>
<td>2/5</td>
<td>4/5</td>
<td>Mise en place de protocoles de sécurité robustes, sauvegardes régulières des données utilisateurs.
</td>
</tr>
</tbody>
</table>
