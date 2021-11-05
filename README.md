# Catawiki Android Assignment - Luciano

## On the implementation for the assignment
### What's been done:

Split the pokemon listing and details into two different modules;

Creating a module for providing testing resources like JUnit 5 extensions, Espresso matchers, and utility functions;

Refactoring the pokémons list module to use the required REST API, using Retrofit;

Refactoring the pokémons list module to use RxJava for threading and exposing the call response through the layers;
Providing unit tests for the pokemon list module;

Making the list position persist through configuration change;

Navigation:
1. Creating a module for centralizing the navigation and exposing it to the feature modules through a facade-module.
2. Creating the concrete navigation implementation through the navigation-impl module, still using the navigation component.
Through this strategy, each module still can define its own intra-module navigation (each navigation graph would be imported to the main navigation graph), while the inter-module navigation is kept centralized.
This also allows FeatureA to FeatureB navigation and vice-versa without circular dependency.

Overall code refactoring: removing boiler-plate fragment factories in favour of koin's fragment factory, removing unnecessary code, refactoring code in favour of ktx functions, updating dependencies, etc.

### What's missing or could be done better:

List pagination:
1. The current [android pagination component](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) doesn't favour unit testing, it makes you spread android specific classes through the domain and repository layers (you can check my pagination implementation in [this repository](https://github.com/lucianoluzzi/shopping)).
2. Developing pagination from scratch would demand more time for this assignment, so I prefered to make the concession on pagination than on unit testing.

UI tests for the pokémon list: the previous espresso tests I had started failing when I refactored the modules, and since this was not a priority, I left it out;

Better management of the dependencies: in [this repository](https://github.com/lucianoluzzi/Sweat) I implemented it using kotlin script, which allows for code-completion and navigate to implementation;

### Feedback on the assignment:
Refactoring an already existing repository is nice approach because it takes less time with configurations, class creation, etc., and allows the assignee to focus on the topics that are really important.

Also it shows that you took the time to really look into my github, and that's lovely. :)



## Welcome to the Android developer candidate assignment.

In this task we would like you to do a refactoring of your own repository: https://github.com/lucianoluzzi/pokemons

In this assignment we would like to test your skills on these topics:
1. Separation of concerns (Good layer separation)
2. Threading
3. Testability
4. Communication with REST APIs
5. Android lifecycle
6. Modularisation
7. Git

In order to test your skills in these topics we would like you to refactor the pokemon list feature only (We will exclude the Pokemon details from this refactoring):
1. Separate Pokemon list and details in separate modules
2. Migrate pokemon list module to use Retrofit and ok Http
3. Migrate pokemon list module to use RxJava to manage your data streams
4. API migration (See details below)


# API migration
To fetch the pokemon list we would like you to migrate to this public API: *Poké-API*: https://pokeapi.co

You can use this Endpoint to fetch the List of (paginated) Pokémon species: (`v2/pokemon-species`)
   * Name
   * Image (To get the image use https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/{species_id}.png)

You can keep the same presentation for displaying the list of pokemon as you already have in the forked repo. 

# Bonus points:
1. The List of pokemons could be persisted when rotating the screen (Config change).
2. The list of pokemons could be lazily loaded(Pagination).




