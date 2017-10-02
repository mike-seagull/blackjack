class Dealer: Player("dealer", 0) {

    var hand = Hand()

    override fun turn(shoe: Shoe) {
        if ( !hand.isNaturalBlackJack() ) { // no hits for natural blackjack
            while ((hand.isSoft() && hand.value() == 17) || hand.value() < 17) {
                // hits for less than 17 or soft 17
                hand.draw(shoe.deal())
            }
        }
    }
}