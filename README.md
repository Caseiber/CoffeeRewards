# Coffee Rewards

## Description (Updated 5/21)
  This is a mobile app project designed to help coffee shops (or any other stores that have user reward points) keep track of their customer's points (think Square, but worse and less useful).
  
  This is the current state of the project:
  ![](https://user-images.githubusercontent.com/35345068/119201315-a3a21c80-ba5c-11eb-9bff-a568288ad244.png)
  
## Usage (with current functionality)
  Select whichever customer is making a purchase from the dropdown. Enter the dollar amount of the purchase made by the customer into the 'Enter Purchase Amount' field, and press 'Add Purchase'. The amount of points awarded will appear in the rewards field below. If enough points have been earned to receive a reward, the 'Redeem' rewards button will enable. When pressed, the amount of points needed for the reward will be removed from the total.
  
## Features
### Current
* Android compatibility
* Allows you to input a purchase amount and adds the earned amount of points to the total
* Redeeming points is disabled until there are enough points for a reward
* User dropdown
  * Shows how many points a user has and allows you to choose which user will receive the points

### In Progress
* Memory
  * Currently, all data is lost when app is closed
  
### Future
* Add User capability
 * Testing

### Stretch Goals
* iOS compatibility
* Settings
  * Allow users to change points awarded per dollars spent
  * Allow users to change how many points are needed before a reward is available
  * Even bigger stretch: allow points to be given based on dollar amount spent *or* how many purchases have been made
* Dark mode


## Author
  This project is written in Kotlin by Caroline Seiber for funsies
