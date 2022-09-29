/**************************************************************/
/*           Name: Ross Crowley                               */
/*			 student number: C20410104					      */
/*															  */
/*                                                            */
/**************************************************************/



#define _CRT_SECURE_NO_WARNINGS 1
#define bool int
#define false 0
#define true (!false)
#define MAX 3

//Libraries
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stddef.h>

//Preprocessor Variable
#define SIZE 3

//Stucture for a Car
struct car  {
	char reg[10];
	char make[15];
	char color[15];
	int prevOwners;
	bool resevered;
	float resAmount;

};

//Stucture template for one node
struct LinearNode {
	struct car *element;
	struct LinearNode *next;
};


// Function prototypes
void addNodes();  //adding nodes to front of the front
void viewAllNodes();
void displayMenu();
struct car *findCar(char *);
bool isEmpty();
bool isLegitReg(char *);
void reserveCar(char *);
void unreserveCar(char *);
void viewSpecificCar();
void sellACar(char *);
void viewCarsBasedOffMake(char *);
void viewCarsBasedOffColor(char *);
//void saveToFile(FILE *);
//void getFromFile(FILE *);

// Global Variables
struct LinearNode *front = NULL; //front of front

struct LinearNode *last = NULL; //pointer to last node in front



/**************MAIN FUNCTION*******************/
int main() {

	//FILE *fp;
	int i;

/**
	if ((fp = fopen("cars.dat", "rb")) == NULL) {

		//file DOES NOT exist so get user to input students
			addNodes();
	}
	else {

		getFromFile(*fp);
	}
	*/

	char delay;
	int option = 0;

	do {

		option = processMenuOption();

		if (option == 1) {

			addNodes();
 
		}
		else if (option == 2) {
			
			char reg2[10];

			printf("What is the registration of the car you wish to sell? \n");
			scanf("%s", reg2);

			sellACar(reg2);

		}
		else if (option == 3) {

			system("cls");
			
			int ans;
			char reg1[10];

			printf("Would you like to reserve or unrserve a car. enter 1 for reserve and 2 for unreserve\n");
			scanf("%d", &ans);

			if (ans == 1) {

				printf("What is the car reg you would like to reserve? \n");
				scanf("%s", reg1);

				reserveCar(reg1);

			}
			else if (ans == 2) {

				printf("What is the car reg you would like to unreserve? \n");
				scanf("%s", reg1);

				unreserveCar(reg1);
			}
			
		}
		else if (option == 4) {


			system("cls");

			int option1;

			do {
				printf("--------VIEWING OPTIONS-------- \n\n");
				printf("1. View all cars in the showroom\n");
				printf("2. View specific makes of cars from the showroom\n");
				printf("3. View specific cars based on color from the showroom\n");

				printf("Enter the menu option : ");
				scanf("%d", &option1);

				if (option1 < 1 || option1 > 3) {

					printf("please enter a valid option 1-3\n");

				}

			} while (option1 < 1 || option1 > 3);

			if (option1 == 1) {

				viewAllNodes();
			}
			else if (option1 == 2) {

				char make[20];
				printf("What is the make of the car you wish to view? \n");
				scanf("%s", make);

				viewCarsBasedOffMake(make);
			}else if (option1 == 3) {

				char color[20];
				printf("What is the color of the car you wish to view? \n");
				scanf("%s", color);

				viewCarsBasedOffColor(color);
			}
		}
		else if (option == 5) {

			system("cls");

			 viewSpecificCar();
		}
		else if (option == 6) {

			system("cls");

			printf("\n\n\n You have exited the system. Goodbye");

		}


	}while( option != 6);




	delay = getchar();
	delay = getchar();
}

/*******************************************/


/**********ADD THREE NODES TO THE LIST******************/
// Each new node is added to the front of the front

void addNodes() {
	
	system("cls");

	char registration[10];
	char make[15];
	char color[15];
	int pOwners =0;
	bool reserved = false;
	float amount=0;
	struct LinearNode *aNode;
	struct car *anElement;

	
	// Add a registration and ensure it follows the right pattern
	do {
		printf("Enter a registration for car : ");
		scanf("%s", registration);

		if (isLegitReg(registration) != true) {

			printf("Please enter a legitmate registration\n ");
		}

	} while (isLegitReg(registration) != true);

	//add make and color 
		printf("Enter car make for car : ");
		scanf("%s", make);
		printf("Enter car color for car : ");
		scanf("%s", color);

	//add number of previos owners and ensuring its less than 3
		do {
			printf("Enter number of previous owners for car : ");
			scanf("%d", &pOwners);

			if (pOwners <= 0 || pOwners > 3) {

				printf("Oops. Cannot have more than 3 previous owners or less than 0 owners \n");

			}
		} while (pOwners <= 0 || pOwners > 3);

		// add cost to reserve a car ensuring its between 500 and 1500
		do {
			printf("Enter how much it cost to reserve car (Min = €500 and Max = €1500): ");
			scanf("%f", &amount);

			if (amount <= 500 || amount > 1500) {

				printf("Cost cannot be greater than €1500 or less than €500\n");

			}


		} while (amount <= 500 || amount > 1500);
		//create space for new data element
		anElement = (struct car *)malloc(sizeof(struct car));

		// create space for new node
		aNode = (struct LinearNode *)malloc(sizeof(struct LinearNode));

		//add car to data node
		strcpy(anElement->reg, registration);
		strcpy(anElement->make, make);
		strcpy(anElement->color, color);
		anElement-> prevOwners = pOwners;
		anElement->resAmount = amount;
		anElement->resevered = reserved;

		if (aNode == NULL)
			printf("Error - no space for the new node\n");
		else { // add data part to the node
			aNode->next = NULL;
			aNode->element = anElement;

			//add node to front of the node
			if (isEmpty())
			{
				front = aNode;
				last = aNode;
			}
			else {
				aNode->next = front;
				front = aNode;
			} //end else
		}//end else
	
} //end addNodes

// Function to view all nodes
void viewAllNodes() {
	struct LinearNode *current;

	system("cls");

	if (isEmpty())
		printf("Error - there are currently no cars in the showroom\n");
	else {
		current = front;
		while (current != NULL) {
			printf("Car Reg:  %s\n", current->element->reg);
			printf("Car make:  %s\n", current->element->make);
			printf("Car color:  %s\n", current->element->color);
			printf("Number of previous owners:  %d\n", current->element->prevOwners);
			printf("Cost to reserve the car:  %f\n", current->element->resAmount);
			printf("Car is reserved:  \n", current->element->resevered);
			printf("--------CAR--------  \n\n\n");
			current = current->next;
		} //end while
	}//end else
} //end viewAllNodes

// Function to ensure that a legitimate reg has been entered 
bool isLegitReg(char *reg) {

	if (strlen(reg) != 8) return false;
	if (reg[0] != '1' && reg[0] != '2') return false;
	if (reg[1] == '1' && !isdigit(reg[1])) return false;
	if (reg[2] != 'D') return false;
	if (reg[3] != '1' && reg[3] != '2') return false;
	if (!isdigit(reg[4])) return false;
	if (!isdigit(reg[5])) return false;
	if (!isdigit(reg[6])) return false;
	if (!isdigit(reg[7])) return false;

	return true;

}

void displayMenu() {

	//system("cls");
	printf("--------MENU OPTIONS-------- \n\n");
	printf("1. Add new car to showroom\n");
	printf("2. Sell a car from the showroom\n");
	printf("3. Reserve/Unreserve a car in the showroom\n");
	printf("4. View all cars in the showroom\n");
	printf("5. View a specific car in the showroom\n");
	printf("6. Exit the system\n");

}

// Couldnt figure out why it was displaying the addresses of the variables and not the values
void viewSpecificCar() {

	system("cls");

	struct LinearNode *current;
	char reg1[10];
	struct car *aCar;

	printf("What is the car reg you would like to view? \n");
	scanf("%s", reg1);

	aCar = findCar(reg1);

	printf("--------CAR--------  \n\n\n");
	printf("Car Reg:  %s\n", aCar->reg);
	printf("Car make:  %s\n", aCar->make);
	printf("Car color:  %s\n", aCar->color);
	printf("Number of previous owners:  %d\n", aCar->prevOwners);
	printf("Cost to reserve the car:  %f\n", aCar->resAmount);
	printf("Car is reserved:  \n", aCar->resevered);
	

}

//Function to process menu option
int processMenuOption() {

	int menuOption = 0;

	displayMenu();

	do {

		printf(" Please input menu option(i.e. 1)\n");
		scanf("%d", &menuOption);

		if (menuOption < 1 || menuOption > 6) {

			printf("Invalid menu option. Please input a valid menu option (1-6)");
		}

	} while (menuOption < 1 || menuOption > 6);

	return menuOption;
}

// Function to find car in the linkelist by passing in a reg
struct car *findCar(char *reg1) {
	struct LinearNode *current, *previous;
	bool notFound = true;

	if (isEmpty())
		printf("Error - there are currently no cars in the showroom\n");
	else {
		current = previous = front;

		while (notFound && current != NULL) {
			if (strcmp(reg1 ,current->element->reg) == 0) {
				notFound = false;

				printf("The car you were looking for has been found\n");

			}
			else {
				previous = current;
				current = current->next;
			}//end else
		} //end while

		if (notFound) {
			printf("Error - there is no such car with a registration value of %d\n", reg1);
			return NULL;
		}
		else return current;

	}//end else
}// end deleteNode 

//Function to reserve a car 
void reserveCar(char *reg2) {

	struct LinearNode *current, *previous;
	bool notFound = true;
	bool res = true;
	char ans[5];

	if (isEmpty())
		printf("Error - there are currently no cars in the showroom\n");
	else {
		current = previous = front;

		while (notFound && current != NULL) {
			if (strcmp(reg2, current->element->reg) == 0) {
				notFound = false;

				printf("The car you were looking for has been found\n");


				if (current->element->resevered == true) {

					printf("This car is reserved\n");

				}
				else {

					printf("You are in luck, this car is not reserved\n, We will reserve it for you now \n\n");

					current->element->resevered = res;

				}
			}
			else {
				previous = current;
				current = current->next;
			}//end else
		} //end while

		if (notFound) {
			printf("Error - there is no such car with a registration value of %d\n", reg2);
			return NULL;
		}


	}
}

// Function to view cars based off of make. I could not get this to work properly but dont see my problem 
void viewCarsBasedOffMake(char *make) {
	struct LinearNode *current, *previous;
	bool notFound = true;

	if (isEmpty())
		printf("Error - there are currently no cars in the showroom\n\n");
	else {
		current = previous = front;

		while (current != NULL) {
			if (strcmp(make, current->element->make) == 0) {

				notFound = false;
			}
			else {
				previous = current;
				current = current->next;
			}//end else

		}
		if (notFound) {
			printf("Error - there is no such car with that make, value of %d\n", make);
			
		}else {

			printf("Car Reg:  %s\n", current->element->reg);
			printf("Car make:  %s\n", current->element->make);
			printf("Car color:  %s\n", current->element->color);
			printf("Number of previous owners:  %d\n", current->element->prevOwners);
			printf("Cost to reserve the car:  %f\n", current->element->resAmount);
			printf("Car is reserved:  \n", current->element->resevered);
			printf("--------CAR--------  \n\n\n");
			
		}
	}//end else
}
//Function to view cars based off of make.I could not get this to work properly but dont see my problem
void viewCarsBasedOffColor(char *color) {
	struct LinearNode *current, *previous;
	bool notFound = true;

	if (isEmpty())
		printf("Error - there are currently no cars in the showroom\n");
	else {
		current = previous = front;

		while (current != NULL) {
			if (strcmp( color, current->element->color) == 0) {

				notFound = false;
			}
			else {
				previous = current;
				current = current->next;
			}//end else

		}
		if (notFound) {
			printf("Error - there is no such car with this color:  %d\n", color);

		}
		else {

			printf("Car Reg:  %s\n", current->element->reg);
			printf("Car make:  %s\n", current->element->make);
			printf("Car color:  %s\n", current->element->color);
			printf("Number of previous owners:  %d\n", current->element->prevOwners);
			printf("Cost to reserve the car:  %f\n", current->element->resAmount);
			printf("Car is reserved:  \n", current->element->resevered);
			printf("--------CAR--------  \n\n\n");
			//current = current->next;
		}
	}//end else
}

//Function to unreserve a car
void unreserveCar(char *reg2) {

	struct LinearNode *current, *previous;
	bool notFound = true;
	bool res = false;
	char ans[5];

	if (isEmpty())
		printf("Error - there are currently no cars in the showroom\n");
	else {
		current = previous = front;

		while (notFound && current != NULL) {
			if (strcmp(reg2, current->element->reg) == 0) {
				notFound = false;

				printf("The car you were looking for has been found\n");


				if (current->element->resevered == true) {

					printf("This car is reserved, We will unreserve it now\n");

					current->element->resevered = res;

				}
			
				else {

					printf("This car is already unreserved \n\n");

				}
			}
			else {
				previous = current;
				current = current->next;
			}//end else
		} //end while

		if (notFound) {
			printf("Error - there is no such car with a registration value of %d\n", reg2);
			return NULL;
		}


	}
}

// Function to sell a car. The function first searches for the reg to see if it exists in the linkedlist 
// if it isFound a check will be done to see if it is reserved/unreserved, if it is reseverd then it will be sold (i.e deleted)
// Otherwise a message is displayed informing only cars that are reserved can be sold.
void sellACar(char *reg2) {

	system("cls");
	system("cls");

	struct LinearNode *current, *previous;
	bool notFound = true;
	bool res = false;
	char ans[5];

	if (isEmpty())
		printf("Error - there are currently no cars in the showroom\n");
	else {
		current = previous = front;

		while (notFound && current != NULL) {
			if (strcmp(reg2, current->element->reg) == 0) {
				notFound = false;

				printf("The car you were looking for has been found\n");


				if (current->element->resevered == true) {

					printf("The sale has been successful, the car will now be removed from the showroom \n");

					if (notFound) {
						printf("Error - there is no such car with a registration value of %d\n", reg2);
						return NULL;
					}
					else {
						if (current == front) {
							front = front->next;
							free(current);
						} //end else
						else {
							previous->next = current->next;
							free(current);
						} //end else
						printf("Car with the registration value of %d has been removed from the showroom\n", reg2);



					}
				}else {
					printf("This car is unreserved. You cannot sell a car that has not been reserved. Please reserve the car before trying to sell \n\n");
				}
			}else {
				previous = current;
				current = current->next;
			}//end else
		} //end while

		system("cls");

		}
	}


// Could not fully understand how to work the file handling with a linkedlist in time for submission

/**
void getFromFile(FILE *fp) {

	struct LinearNode *aNode;
	struct car *anElement;
	
	printf("Retriving cars from file...\n");
	
	//create space for new data element
	anElement = (struct car *)malloc(sizeof(struct car));
	// create space for new node
	aNode = (struct LinearNode *)malloc(sizeof(struct LinearNode));

//	while (fread(sizeof(struct node *)malloc(sizeof(struct LinearNode)), 1, fp) != NULL) {

		
	//}
	

	fclose(fp);
}
//while (fread(sizeof(struct car *)malloc(sizeof(struct car)), 1, fp) != NULL)
*/

//Function to check if LinkedList is empty
bool isEmpty() {
	if (front == NULL)
		return true;
	else
		return false;
}
