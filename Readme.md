# Projet de développement mobile : ChocoCrumble
## Binôme :
Mathilde Guédon, Nicolas Sanson

## Fonctionnalités développées
### HomePage
La première page détaille la liste des catégories de recette.

### Détail de Catégorie
Cette deuxième page affiche la liste des recettes de cette catégorie ainsi que sa description
et implémente un bouton de retour à la première page.

### Détail de Recette
Cette troisième et dernière page affiche les détail de la recette ainsi qu'un lecteur intégré de vidéo youtube si elle est disponible.
Si une vidéo youtube charge en boucle sur fond noir, c'est que la vidéo n'existe plus (a été supprimée ou est indisponible sur youtube).

### Autres ajouts
L'application possède une icône personnalisée.

## Difficultées rencontrées
Un bouton retour de la page recette vers catégorie a tenté d'être implémenté, et s'il est facilement réalisable en tant que bouton, l'intégrer à la ToolBar comme 
le bouton de Catégorie vers Homepage ne permet pas de passer les arguments nécessaires à l'Intent de Catégorie. Ainsi, l'application ne sait pas quelle catégorie
elle doit charger et cela créé un bug. 
