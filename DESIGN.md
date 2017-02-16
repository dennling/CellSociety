1.Provide the high-level design:  

Main calls our Setup class, which initializes the stage, scene, and gets the data necessary to start the game. Setup is also connected to the SceneBuilder, which builds the UI for our program. Setup then passes in the correct game to Simulator, which creates the keyframe and steps through the timeline. The simulator updates the Game, Grid, and Cells according to their respective logics. Specific design goals are described below:

  

On the configuration and visualization aspect:

  

- Setup - Initializing the big name classes such as the Game and the Graph, and the right formatting file based off of the XMLInput. Multiple dependencies: Game, Graph, GameData, and SceneBuilder. Once setup had run, passed the operations off to Simulator.  
  

- Simulator - Setup passes in the correct Game, the SceneBuilder (which contains all the relevant grids and buttons), and the State. Responsible for animation and handling button events. Since the UI action events directly related to the animation and simulation, we decided that this was the best place to include event handlers. Mainly dependent on Game and Graph.  
  

- Superclass Graph - called by simulator, and included in the animation process.Creation of the population number tracking graph. Not directly dependent on any other classes apart of our code.  
  

- Superclass SceneBuilder - called by Setup and passed to Simulator to return buttons and Graph displays. Creates all the specific buttons, shapes, cell placement, etc. of each game. Dependent on Graph and Grid. While setup initializes most of the backend, Scenebuilder and its subclasses are responsible for implementing the actual UI. 
  

On the backend:

  

- GameData - holds all the data for the games that it receives from XMLParser. We chose to make a class to hold all the data because it reduces having to pass specific variables to every class, where we could just pass Data and call methods to receive the data. No dependencies either.  
  

- Superclass Game - One of the major backend components. Responsible for big picture items that are related to all games (gameLogic, initialPositions, etc.).Allows us to reduce repeated code throughout games. Directly dependent on GameData and Grid.  
  

- SuperClass Grid - Class responsible for all the interactions with the cells. By isolating this functions of this class to a single class, we reduce the overall direct dependencies and object passing. This class is in charge of the actual implementation of creating, updating (including neighbors), and changing of the cells in the grid. Only directly dependent on Cells.  
  

- SuperClass Cells - carries all specific information behind the cells. responsible for implementation of updating specific information (such as the specific neighbors, type, color) of the cells. Only directly dependent on NeighborManager.  
  

- NeighborManager - responsible for keeping track of each cell's neighbors, which is directly applicable to gameLogic. Allows the cell class to not worry about managing neighbors. 
  

2.To add a new simulation:

  

- Create a game subclass (you only NEED to implement the abstract classes for the game to function): 
    - Implement the gameLogic method: this takes in the current cell that the grid is looking at and changes the state of that cell based on neighbors 
        - For instance, in Game Of Life, the cell dies if it has less than 2, but more than 3 neighbors 

    - Set default positions: 
        - In case the XML file doesn’t have pre-set positions, all one needs to do is implement a loop based on how many cells they want in the default position, and call randomCellGenerator 

    - Create grid 
        - Create grid using the appropriate Grid class 

- Create a grid subclass (only need abstract) 
    - Implement cell type, which sets the type of Cell you want to fill the grid 
        - For Instance, GameOfLifeGrid implements GameOfLifeCell 

    - Implement resetType, which is called when the user hits the Reset button, so reset the cells to the initial state 

- Create a cell subclass (only need abstract) 
    - Implement specifyNeighborCell, which just declares what type of cell the neighbors should be 
        - They should always have the type “neighbor”, but can have any other conditions besides the type 

    - Implement setPossibleNeighbors, which is a vector that has the positions that the NeighborManager should move to check the neighbors  
        - The vector is a 2D array, and each value should be either -1, 1, or 0, unless your game contains very odd rules 

    - Implement checkType, which checks to make sure that the set type is a valid type for that specific cell. If not, returns exception 
    - Implement setColor, which sets the color of the cell’s shape based off the type 

- Create a graph subclass  
    - Fill in the initializeSeries, updateGraph, and clear methods appropriately. These methods are very straightforward to implement as they should do exactly what their method names say. A new CSS file can also be created here to customize the graph visuals to the specific simulation. 

- Add case to switch statement in Setup class 
    - A keyword string must be created for the new simulation. The switch in the Setup method must now have a case for that keyword - in this case, the user would have to initialize the new Game and Graph subclasses as well as the new CSS stylesheet.  

- Create an XML file with all the correct parameters necessary for the simulation 
    - In GameData, create getters for the parameters that are necessary for each simulation so the game can access the pertinent variables 

  

Our design was created such that there is a clear framework as far as adding a new simulation. Moreover, there is never a need to edit superclasses or the Setup class.

  
  

To add new grid types:

- Add a case for the grid type in the XML file and in the data file 
    - Add a getter for the “gridType” key in the XML file, and use that getter to pass it to the NeighborManager 

- Update the way neighbors are calculated 
    - This logic is updated by adding a case in NeighborManager 

  

To add new cell shapes:

- On the front end, create a new class that extends SceneBuilder.  
    - This class should contain all of the logic and calculations for placing the new shape onto the grid. 

- Add the new case to SetUp and to the XML file 
    - Add a getter for the data for the “cellShape” key in the XML file, and use that getter to pass it to the neighbor manager 
    - This will allow the new type of cell to be initialized to the grid and used by other classes 

- Update the way neighbors are calculated 
    - This logic should be done by adding a case in NeighborManager 

3.Justify any major design choices, including trade-offs 
  

In general, our original implementation handled the extensions quite well. We had to create the neighbor manager class, and we also had to make the SceneBuilder a superclass and create abstract methods to incorporate the additional shapes of cells. But for the most part, we were able to incorporate the extensions we made quite well. I think the way we designed our flow between Game, Cell, Grid, Graphs, and the UI made it easy to extend an additional feature. But, I do think that the extensions forced us to create different classes that ultimately made the design better and more functional. 

  

1) One design decision we made was to create separate cell classes. We thought about creating one general cell class, then having a different class that interpreted the types that we were assigning. This would eliminate the need to type cast the cells in the Game classes, and it would also decrease the number of classes. But, ultimately, it would make the cell class way too long, and I think it improves the functionality and extensibility of our program by allowing a user to simply extend a new cell class than editing the original class. 

  

2) The UI: we originally did all of the logic to determine the cells and the display within the original SceneBuilder class. Which, like number 1, would eliminate more classes, but would extend the original class and limit the extensibility of the project. This allows us to add displays with more shapes easily. 

  

3) One of our major design choices from the beginning was storing our cell locations and calculating neighbors by using a 2D array instead of an ArrayList. On one hand, an ArrayList would increase the flexibility of our program without sacrificing functionality. On the other hand, using the 2D array would made back-end implementation easier to understand. Since they were able to abstract over the 2D array with the Grid package, we were ultimately able

The get the best out of both solutions (flexibility and clear logic).

  

4) Another major design choice we made was making the Graph package instead of just implementing the graph methods within the SceneBuilder. The benefits to how our code is currently constructed is that the SceneBuilder class is more concise and focused and that there is a clear framework in place for someone to add a new type of graph to the project. The opposite point of view would be that the Graph package contains unneeded dependencies and do not provide enough functionality to deserve their own classes.

5) We also had to decide where we would parse our data. We ended up parsing data in a separate class, but we had discussed potentially using the Game class to parse data. This is because we thought the Game class would be the primary user of the data, but we didn't want to clog up the purpose of the Game class with additional functions. Using the additional class for XML parsing was more flexible and readable. Additionally, the XML parsing in a separate class creates a dependency between the Game class and the XML class. I personally prefer the way we currently do the XML parsing because it maintains it's own function without intruding on the Game function.

6) Similar to the cell class decision, we also debated if we should have a grid superclass. In our final implementation, we chose to maintain a Grid superclass that was to be extended by each simulation. This turned out to be a good idea because some more complicated simulations needed to update the grid in different ways. For example, in Wator world we chose to update the sharks states first before updating the fish in order to not have conflicts between the fish and shark states. A potential different implementation we could have chosen was to have the grid update in the Cell class or in the Game class. This would compact the code and reduce the duplicated code that occurs with a whole new class and it's additional subclasses. However, this would not be as flexible or intuitive because the game and cell classes were not meant to have the update functionality. I would prefer for the implementation to be as the way it is now, with a completely separate Grid superclass.
  
  

4.State any assumptions or decisions made to simplify or resolve ambiguity in functionality 
  

- It is assumed in the SceneBuilder that the GUI will have set dimensions - these are final constants within the code.  
- We simplified the functionality of our program by configuring our XML files in a specific way. We check for certain keys in each file and assume that the correct game will have all of it’s correct parameters in order to run. If it doesn’t have a necessary parameter, we throw an exception.  
- We assume triangles only have 3 neighbors to resolve ambiguity about the triangle cell. 
- We chose to configure the cells on the grid depending on the size of the grid in order to resolve ambiguity of how to place the cells. This means we calculate the cell size rather than taking it in as a parameter. 
- We assume that the only parameters that may be inputs for cell shape and grid type are triangles, hexagons, or squares and toroidal or finite respectively. Any other types will cause an exception to be thrown. 
- To resolve ambiguity in functionality, we also decided to group our classes in packages. This allows users to browse similar classes based on functionality.