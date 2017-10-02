import java.util.*

abstract class Player(var name:String = "", var money:Int = 100) {
    var hands = ArrayList<Hand>()

    open fun turn(dealerShows: Shoe.Card, shoe: Shoe){}
    open fun turn(shoe: Shoe){}
    fun split(hand: Hand, shoe: Shoe) {
        var hand1 = Hand()
        hand1.draw(hand.cards.get(0))
        hand1.draw(shoe.deal())
        var hand2 = Hand()
        hand2.draw(hand.cards.get(1))
        hand2.draw(shoe.deal())
        hands.remove(hand)
        hands.add(hand1)
        hands.add(hand2)
    }
}