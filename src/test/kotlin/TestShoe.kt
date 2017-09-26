import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestShoe {

//    private var shoe = Shoe()
    private var shoe = Shoe()

    @Before
    fun setup() {
        shoe = Shoe()
    }
    @Test
    fun testShoeLength() {
        for (i in 1..8) { // max decks in blackjack is 8
            var newShoe = Shoe(i)
            Assert.assertEquals(newShoe.cards.size, newShoe.numberOfDecks * 52)
        }
    }
    @Test
    fun testShuffle() {
        var newShoe =  Shoe(1)
        Assert.assertTrue(newShoe == shoe)
        newShoe.shuffle()
        Assert.assertFalse(newShoe == shoe)
    }
    @Test
    fun testDeal() {
        Assert.assertEquals(shoe.cards.size, 52)
        var card = shoe.deal()
        Assert.assertEquals(Shoe.Card(Shoe.Card.Rank.ACE, Shoe.Card.Suit.CLUBS), card)
        Assert.assertEquals(shoe.cards.size, 51)
    }

}