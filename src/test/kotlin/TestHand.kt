import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestHand {
    private var hand = Hand()

    @Before
    fun setup() {
        hand.cards.clear()
    }
    @Test
    fun testDraw() {
        var s = Shoe()
        hand.draw(s.deal())
        Assert.assertEquals(1, hand.cards.size)
        hand.draw(s.deal())
        Assert.assertEquals(2, hand.cards.size)
    }
    fun allPossible2CardBlackJacks() {
        for (suit in Shoe.Card.Suit.values()) {
            for (rank in Shoe.Card.Rank.TEN.value..Shoe.Card.Rank.KING.value) {
                // all values of 10 from every suit
                for (aceSuit in Shoe.Card.Suit.values()) {
                    var ace = Shoe.Card(Shoe.Card.Rank.ACE, aceSuit) // all possible aces of any suit
                    var tenCard = Shoe.Card(Shoe.Card.Rank.from(rank), suit)  //  all possible 10's of any suit
                    hand.draw(ace)
                    hand.draw(tenCard)
                }
            }
        }
    }
    @Test
    fun testValue() {
        for (card1Suit in Shoe.Card.Suit.values()) {
            for (card1Rank in Shoe.Card.Rank.DEUCE.value..Shoe.Card.Rank.NINE.value) {
                for (card2Suit in Shoe.Card.Suit.values()) {
                    for (card2Rank in Shoe.Card.Rank.DEUCE.value..Shoe.Card.Rank.NINE.value) {
                        var card1 = Shoe.Card(Shoe.Card.Rank.from(card1Rank), card1Suit)
                        hand.draw(card1)
                        var card2 = Shoe.Card(Shoe.Card.Rank.from(card2Rank), card2Suit)
                        hand.draw(card2)
                        if (card1Rank+card2Rank != hand.value()) {
                            Assert.fail("\n$card1 \n and \n $card2 \n do not equal ${hand.value()}")
                        } else {
                            Assert.assertEquals(card1Rank + card2Rank, hand.value())
                        }
                        hand.cards.clear()
                    }
                }
            }
        }

    }
}