# Garden of Eden
This is a mini-ecological simulator. It seeds a set amount of organisms with a 
certain genetic code as well as a set amount of initial foodstuffs. Each organism will
attempt to move around and eat other organisms. Those who don't eat another organism
within a set amount of time will starve to death.

When one organism eats another, it has a chance of reproducing, 
which introduces genetic mutations. Each
genetic code corresponds to a set strength/fitness. The more fit creature will always
be able to consume the less fit creature.

It's rather cool to see what happens to the population over time, as well as seeing how
quickly this genetic algorithm produces creatures of maximal fitness.

# Commands
'r' followed by a '#' -> Run the simulation for X turns.
'p' -> Displays relevant data on all organisms to terminal. Also moves forward one turn.
'q' -> Quits
everything else -> Moves forward one turn.

# Constants
It's a quick way of modifying key aspects of the simulator!

# Disclaimer for Potential Employers
Indentation was likely destroyed by me emailing myself the code.
Code was written in ~ 1.5 hours, and was a personal whim of mine.
While the code itself is nothing to be proud of, I am proud of how cool the simulator is.