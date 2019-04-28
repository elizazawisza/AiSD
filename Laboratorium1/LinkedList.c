#include<stdio.h>
#include<stdlib.h>
#include <time.h>


int counter = 0;

//Linked list node
typedef struct Node {
  int value;
  struct Node *next;
} node;

//Insert new element at the front of the linked list

void insert(node **head, int new_value){
   node *new_node = malloc(sizeof(node));
   new_node->value = new_value;
   new_node->next = *head;
   //new element is now the head element
   *head = new_node;
}

//Insert new element at the end of the linked list

void insert_End(node **head, int new_value){
  node *new_node = malloc(sizeof(node));
  node *current_node = *head;

  new_node->value = new_value;
  new_node->next = NULL;
  counter++;

  //if the list is empty, the new_Element become first
  if(*head == NULL){
    *head = new_node;
  }else{
    //finding the last element of the list
    while(current_node->next != NULL){
      counter++;
      current_node = current_node->next;
    }
    //adding new element to the end of list
    current_node->next = new_node;
  }
}

//Delete first node from the beginning of the list which contain the given value

void delete(node** head, int value){
  node *previous_node;
  node *current_node = *head;
  //first elemnt is looking value
  if(current_node != NULL && current_node->value == value){
    counter = counter + 2;
    *head = current_node->next;
    free(current_node);
    return;
  }
  while(current_node != NULL && current_node->value != value){
    counter = counter + 2;
    previous_node = current_node;
    current_node = current_node->next;
  }
  if(current_node == NULL){
    counter++;
    return;
  }

  previous_node->next = current_node->next;
  free(current_node);
}

//Print the whole list

void print_List(node* head){
  node* current_node = head;
  while(current_node != NULL){
    printf("%d ", current_node->value);
    current_node = current_node->next;
  }
}

//Check whether the list is empty or not

int isempty(node* head){
  counter++;
  if(head == NULL){
    return 1;
  }else{
    return 0;
  }
}

//Find element contains the given value and move it to the beginning of the
//linked list

int findMTF(node** head, int value){

  counter++;
  if(head != NULL){
    node *newHead;
    node *current_node = *head;
    counter++;
    //first elemnt is looking value
    if(current_node->value == value){
      return 1;
    }
    counter++;
    while(current_node->next != NULL){
      counter = counter + 2;
      if(current_node->next->value == value){
        newHead = current_node->next;
        current_node->next = current_node->next->next;
        newHead->next = *head;
        *head = newHead;

        return 1;
      }
      current_node = current_node->next;
    }
  }
  return 0;
}

//Find element contains the given value and move it one place to the beginning
//of the linked list

int findTRANS(node** head, int value){
  node *current_node = *head;
  node *previous_node = *head;
  node *temporary_node;

  counter++;
  if(head != NULL){
    counter++;
    //first elemnt is looking value
    if(current_node->value == value){
      return 1;
    }
    counter++;
    if(current_node->next != NULL){
        counter++;
        if(current_node->next->value == value){
            temporary_node = current_node->next;
            previous_node->next = temporary_node->next;
            temporary_node->next = previous_node;
            *head = temporary_node;
            return 1;
        }
    }
    counter++;
  while(current_node->next != NULL){
    counter = counter + 2;
    if(current_node->next->value == value){
      temporary_node = current_node->next;
      current_node->next = current_node->next->next;
      previous_node->next = temporary_node;
      temporary_node->next = current_node;
      return 1;
    }
    previous_node = current_node;
    current_node = current_node->next;

  }
}
  return 0;
}

void testMTF(){
  int max = 0;

  node *test_list = NULL;

  time_t tt;
  int myrand = time(&tt);
  srand(myrand);

  for (int i = 0; i < 100; i++) {
      counter++;
      insert_End(&test_list, rand() %100 + 1);
  }

  while(!isempty(test_list)){
    counter++;
    for(int i=1; i<=100; i++){
      counter++;
      if(findMTF(&test_list, i)){
        counter++;
        max = i;
      }
    }
    delete(&test_list, max);
  }

  printf("MTF: %d\n", counter);

}

void testTRANS(){
  int max = 0;
  node *test_list = NULL;

  time_t tt;
  int myrand = time(&tt);
  srand(myrand);

  for (int i = 0; i < 100; i++) {
      counter++;
      insert_End(&test_list, rand() %100 + 1);
  }


  while(!isempty(test_list)){
    counter++;
    for(int i=1; i<=100; i++){
      counter++;
      if(findTRANS(&test_list, i)){
        counter++;
        max = i;
      }
    }
    delete(&test_list, max);
  }
  printf("TRANS: %d\n", counter);
}

int main(){

    //TRANS finding
    testTRANS();

    counter = 0;

    //MTR finding
    testMTF();
}
