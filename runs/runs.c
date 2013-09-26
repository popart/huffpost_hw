#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct arrayRet {
	int* array;
	int len;
} arrayRet;

arrayRet findConsecutiveRuns(int array[], int len, int runLen);
arrayRet findConsecutiveRuns3(int array[], int len);
void printIntArray(char* name, int input[], int len);
void runTest(int input[], int len);

int main() {
	int TEST_LEN = 100;
	int test[TEST_LEN];
	int i;

	//init random input [0,10]
	srand(time(NULL));
	for(i=0; i<TEST_LEN; i++) {
		test[i] = rand()%4; 
	}
	runTest(test, TEST_LEN);

	//don't wanna do array of array pointers right now
	int test2[] = {1,2,3,4,5,6,7,8,9,10};
	int test3[] = {0,0,0,0,0,0,0,0,0,0};
	int test4[] = {10,9,8,7,6,5,4,3,2,1};
	int test5[] = {1,2,3,4,5,4,3,2,1,0};

	runTest(test2, 10);
	runTest(test3, 10);
	runTest(test4, 10);
	runTest(test5, 10);
	return 0;
}

void runTest(int input[], int len) {
	printIntArray("input", input, len);
	arrayRet x = findConsecutiveRuns3(input, len);
	printIntArray("output", x.array, x.len);
	free(x.array);
	printf("\n");
}

arrayRet findConsecutiveRuns3(int input[], int len) {
	return findConsecutiveRuns(input, len, 3);
}

//find start indices of 3-number consecutive runs (+/-)
arrayRet findConsecutiveRuns(int input[], int len, int runLen) {
	int *ret = malloc((len-2) * sizeof(int)); //cuz def won't be last two...
	//could also use a flag for (+/-) and just one count
	//would have to set the first value...
	int countUp = 1;
	int countDown = 1;

	int head = input[0]; //currentVal
	int retI = 0; //index of return

	int diff; 
	int i; //index of iterator 
	for(i=1; i<len; i++) {
		diff = input[i]-head;
		head = input[i];

		if(diff == 1 ) { // +1
			if(++countUp >= runLen) {
				countUp = runLen; countDown = 1;
				ret[retI] = i-(runLen-1);
				retI++; //if anything found, retI > 0
			}
		}
		else if(diff == -1) { //-1
			if(++countDown >= runLen) {
				countDown = runLen; countUp = 1;
				ret[retI] = i-(runLen-1);
				retI++; 
			}
		}
		else {
			countUp = 1; countDown = 1;
		}
	}
	arrayRet ar; 
	ar.array = ret;
	ar.len = retI; //stays 0 if nothing found

	return ar;
}

void printIntArray(char* name, int input[], int len) {
	int i;

	printf("%s: [", name);
	for(i=0; i<len; i++) {
		if(i < len-1) printf("%d, ", input[i]); //comma
		else printf("%d", input[i]); //no comma at end
	}
	printf("]\n");
}
