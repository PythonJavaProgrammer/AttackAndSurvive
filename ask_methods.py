def ask_numeric(*args):
    # This function asks for a selection of string values,
    # each of which it then creates an index for and
    # prints them out as options for the user to select by
    # entering in the index value

    # This loop makes the question get answered infinitely
    # until the user  gives an answer in the specified
    # index range

    answer_accepted = False
    while not answer_accepted:
        print("Would you like to: ")
        length = len(args)
        for i in range(0, length):
            print(f"{i + 1}: {args[i]}")

        try:
            answer = int(input(f"Please enter your answer: (1 - {length}): ")) - 1

            if (answer > length) or answer < 0:
                print("The answer was not in the specified range.\n")
            else:
                answer_accepted = True
                return answer
        except ValueError:
            print("Your answer is not of type int. Please try again.\n")


def ask_binary(question: str):
    answer_accepted = False
    while not answer_accepted:
        answer = input(f"{question}? (y/n): ")
        if not ((answer == "y") or (answer == 'n')):
            print("I\'m sorry, I didn\'t get that. Please rather enter \'y\' or \'n\'.\n")
        elif answer == "y":
            print("You answered yes.")
            break
        elif answer == "n":
            print("You answered no.")
            break
