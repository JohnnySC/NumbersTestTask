package com.github.johnnysc.numberstesttask.main.presentation

import com.github.johnnysc.numberstesttask.numbers.presentation.Communication

/**
 * @author Asatryan on 01.10.2022
 */
interface NavigationCommunication {

    interface Observe : Communication.Observe<NavigationStrategy>

    interface Mutate : Communication.Mutate<NavigationStrategy>

    interface Mutable : Observe, Mutate

    class Base : Communication.SingleUi<NavigationStrategy>(), Mutable
}