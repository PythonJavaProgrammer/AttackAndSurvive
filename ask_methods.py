def ask_numeric(*args, prompt="Would you like to: "):
    """
    This function asks for a selection of string values, each of which it then
    creates an index for and prints them out as options for the user to select
    by entering in the index value.

    Parameters:
    - args: Variable number of string arguments.
    - prompt (str): Prompt for the user (default is "Would you like to: ").

    Returns:
    - int: The selected option index.
    """
    answer_accepted = False

    while not answer_accepted:
        print(prompt)
        length = len(args)

        for i in range(length):
            print(f"{i + 1}: {args[i]}")

        try:
            answer = int(input(f"Please enter your answer (1 - {length}): "))

            if 1 <= answer <= length:
                answer_accepted = True
                return answer
            else:
                print("The answer was not in the specified range.\n")

        except ValueError as e:
            print(f"Error: {e}. Your answer is not of type int. Please try again.\n")


def ask_binary(question: str):
    """
    This function receives a question which it asks the user, and waits
    for a yes or no answer. and returns it as a boolean value.

    Parameters:
    - question (str): The prompt for the user to respond to

    Returns:
    - answer: The selected yes or no as a boolean True or False respectively
    """
    answer = None
    answer_accepted = False
    while not answer_accepted:
        answer = input(f"{question}? (y/n): ")
        if not ((answer == "y") or (answer == 'n')):
            print("I\'m sorry, I didn\'t get that. Please rather enter \'y\' or \'n\'.\n")
        elif answer == "y":
            print("You answered yes.")
            answer = True
            break
        elif answer == "n":
            print("You answered no.")
            answer = False
            break

    return answer
