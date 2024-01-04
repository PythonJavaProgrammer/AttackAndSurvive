from ask_methods import *


def prompt(text: str):
    """
    This function prompts the user at the required amount of text
    to slow the game down and give the user time to read
    :param text: (str) The text for the prompt
    :return: None
    """
    trash_variable = input(text)
    del trash_variable


if __name__ == "__main__":
    play = ask_binary("Would you like to play Attack and Survive")
    if play:
        false_name = True
        while false_name:
            name = ask_numeric("John", "Felix", "Landon", "New Name", prompt="Choose your name: ", direct_answer=True)
            if name == "New Name":
                name = str(input("Type in the new name here: "))
            false_name = not ask_binary(f"Your name will be {name}. Is this your choice?")

    else:
        print("Goodbye")
