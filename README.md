# Personal Project : MemoryMaster

## App Discription

The project is a flash-card mode application that aims to help learner to memorize better
, and it could use **Chatgpt** API to generate some text to help learner to better understand the content of flashcards.

## Function

#### The *Basic* functions:
- create a list that contains flash-cards
- create, store, and delete flash-cards in a list
- typing text, typing mathematical equations, adding pictures.
 
#### The *Advanced* functions:
- generate a little quiz that is made of flash-cards in the list in a random order for users
- generate related texts to help learns understand the context on flash-cards
- visually showing users the priorities of flash-cards that need to be remembered
- change the style of flash-cards, for example light-mode or dark-mode

## Potential users

The project will be useful for people how need to memorize lots of things. Such as students, teachers, and programmers.

## Challenges 

This project will involve databases, the use of chatgpt API, and Graphic interface, which I'm not really familiar with. It's really challenging, but also an opportunity for me to construct a sight for how to build up a software. The challenges are the place where most interest to me.

## User stories
- As a user, I want to create an empty list of flash-cards.
- As a user, I want to delete a list.
- As a user, I want to create, delete a flash-card in a flash-card.
- As a user, I want to add content to the front and back side of the flashcard.
- As a user, I want to change the content of the flash card in the current side.
- As a user, I want to flip the side the flash-card
- As a user, I want to be able to save my to-do list to file (if I so choose)
- As a user, I want to be able to be able to load my to-do list from file (if I so choose)
- As a user, I want to go back to last level;

## Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking create button
, you will need to provide a name for the list.
- You can delete the list by clicking delete button, then the button will be red, and you now click the list that you want to
delete.
- You can enter the list by simply click the list
- You can generate flash card by clicking create button, then you will need to enter the text for front side and back side
- You can delete the flash card by clicking delete button, then the button will be red, and you now click the card that you want to
    delete.
- You can flip the flash card by simple click the flash card when not button is red.
- You can change the name of the list or the text of current side of flash card by clicking change button,
then the color of button will be red, and click the list or the card that you want to change
- You can locate my visual component by starting the application, there will be a picture, which located in src/main/ui/images
- You can save the state of my application by clicking the save button
- You can reload the state of my application by clicking the load button

## Phase 4: Task 2
Sun Apr 07 16:29:47 PDT 2024 List created.
Sun Apr 07 16:29:48 PDT 2024 List created.
Sun Apr 07 16:29:49 PDT 2024 List created.
Sun Apr 07 16:29:51 PDT 2024 List deleted from a folder.
Sun Apr 07 16:29:53 PDT 2024 List name changed.
Sun Apr 07 16:29:58 PDT 2024 Flash card added to the list.
Sun Apr 07 16:30:00 PDT 2024 Flash card added to the list.
Sun Apr 07 16:30:06 PDT 2024 Flash card added to the list.
Sun Apr 07 16:30:09 PDT 2024 Flash card flipped.
Sun Apr 07 16:30:10 PDT 2024 Flash card flipped.
Sun Apr 07 16:30:11 PDT 2024 Flash card flipped.
Sun Apr 07 16:30:11 PDT 2024 Flash card flipped.
Sun Apr 07 16:30:11 PDT 2024 Flash card flipped.
Sun Apr 07 16:30:12 PDT 2024 Flash card flipped.
Sun Apr 07 16:30:13 PDT 2024 Flash card flipped.
Sun Apr 07 16:30:17 PDT 2024 Flash card text changed.
Sun Apr 07 16:30:18 PDT 2024 Flash card flipped.
Sun Apr 07 16:30:20 PDT 2024 Flash card flipped.

## Phase 4: Task 3
For my flash card application, I use "Folder", "FlashList", and "FlashCard" as my model.
FlashCard application will automatically create a folder that is used to hold lists, then lists 
have a list to store flashCard. We will use Json file to read and reload data of the application.
While the application is running, we will add event to EventLog class which has a Collection of Event.
As the end of the application, if it's closed by the user, the application will create an object of 
"Dialog", which is used to display all the event logs that were stored.

### If I have more time what can I improve
As you can see, the structure of my application is simple. For folder, flash list, and flash card classes, 
they have actually really similar functionalities. Therefore, make folder abstract and flash list and flash card extend the folder 
could decrease the complexity of the codes. In this case, I don't need to write too much similar codes that have similar functions.

MoreOver, for the UI package, my final version code of FlashCardApp only has two class. To make the codes easier to debug and 
maintain, I should make different functions into different classes just like what Dialog did. For different buttons with different function
I could reference the example that are provided to us. Use abstract class and extends structure could make the whole structure more intuitive
and efficient.
