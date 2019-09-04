package nl.quintor.solitaire.game;

import nl.quintor.solitaire.models.card.Card;
import nl.quintor.solitaire.models.deck.Deck;
import nl.quintor.solitaire.models.deck.DeckType;
import nl.quintor.solitaire.models.state.GameState;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Stack;

/**
 * Library class for GameState initiation and status checks that are called from {@link nl.quintor.solitaire.Main}.
 * The class is not instantiable, all constructors are private and all methods are static.
 */
public class GameStateController {
    private GameStateController(){}

    /**
     * Creates and initializes a new GameState object. The newly created GameState is populated with shuffled cards. The
     * stack pile and column maps are filled with headers and Deck objects. The column decks have an appropriate number
     * of invisible cards set.
     *
     * @return a new GameState object, ready to go
     */
    private static void addCardsFromStack(int amount, Stack<Card> source, Deck destination){
        for(int i = 0; i < amount; i++){ //54 variants (0-53)
            destination.add(source.pop());
        }
    }

    private static void addEmptyDecks(String[] keys, Map<String, Deck> mapDecks){
        for (String key : keys) {
            mapDecks.put(key, new Deck());
        }
    }

    private static void addCardsToColumns(String[] keys, int[] amountPerColumn, Stack<Card> source, Map<String, Deck> columns){
        for(int i = 0; i < keys.length; i++){
            addCardsFromStack(amountPerColumn[i], source, columns.get(keys[i]));
            columns.get(keys[i]).setInvisibleCards(columns.get(keys[i]).size()-1);
        }
    }

    public static GameState init(){
        Stack<Card> cards = new Stack<Card>();
        for(int i = 0; i < 54; i++){ //54 variants (0-53)
            cards.push(new Card(i));
        }

        GameState state = new GameState();
        addCardsFromStack(1, cards, state.getStock()); //stock
        addCardsFromStack(23, cards, state.getWaste());

        addEmptyDecks(new String[]{"SA", "SB", "SC", "SD"}, state.getStackPiles());
        addEmptyDecks(new String[]{"A", "B", "C", "D", "E", "F", "G"}, state.getColumns());

        addCardsToColumns(new String[]{"A", "B", "C", "D", "E", "F", "G"}, new int[]{1,2,3,4,5,6,7}, cards, state.getColumns());

        return state;
    }

    /**
     * Applies a score penalty to the provided GameState object based on the amount of time passed.
     * The following formula is applied : "duration of game in seconds" / 10 * -2
     *
     * @param gameState GameState object that the score penalty is applied to
     */
    public static void applyTimePenalty(GameState gameState){
        // TODO: Write implementation
    }

    /**
     * Applies a score bonus to the provided GameState object based on the amount of time passed. Assumes the game is won.
     * When the duration of the game is more than 30 seconds then apply : 700000 / "duration of game in seconds"
     *
     * @param gameState GameState object that the score penalty is applied to
     */
    public static void applyBonusScore(GameState gameState){
        // TODO: Write implementation
    }

    /**
     * Detects if the game has been won, and if so, sets the gameWon flag in the GameState object.
     * The game is considered won if there are no invisible cards left in the GameState object's columns and the stock
     * is empty.
     *
     * @param gameState GameState object of which it is determined if the game has been won
     */
    public static void detectGameWin(GameState gameState){
        // TODO: Write implementation
    }
}
