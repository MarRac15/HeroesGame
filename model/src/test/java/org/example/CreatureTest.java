package org.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CreatureTest {
    @Test
    void x() {
        Creature attacker = new Creature(new DefaultDamageCalculator(), new RangedAttackStrategy());
        Creature defender = new Creature(new DefaultDamageCalculator(), new RangedAttackStrategy());
        defender.setCurrentHp(20);
        defender.setMaxHp(20);
        defender.setAmount(2);
        attacker.setDamage(30);
        attacker.setAmount(1);
        attacker.setMaxHp(20);

        attacker.attack(defender);

        assertThat(defender.getCurrentHp()).isEqualTo(10);
        assertThat(defender.getAmount()).isEqualTo(1);
    }

    @Test
    @Disabled
    void y() {
        Creature attacker = new Creature(new DefaultDamageCalculator(), new RangedAttackStrategy());
        Creature defender = new Creature(new DefaultDamageCalculator(), new RangedAttackStrategy());
        defender.setCurrentHp(9);
        defender.setMaxHp(20);
        defender.setAmount(2);
        attacker.setDamage(30);

        attacker.attack(defender);

//        assertThat(defender.getCurrentHp()).isEqualTo(10);
        assertThat(defender.getAmount()).isEqualTo(0);
    }

    @Test
    void z() {
        Creature attacker = new Creature(new DefaultDamageCalculator(), new RangedAttackStrategy());
        Creature defender = new Creature(new DefaultDamageCalculator(), new RangedAttackStrategy());
        defender.setCurrentHp(20);
        defender.setMaxHp(20);
        defender.setAmount(1);
        defender.setDamage(3);

        attacker.setDamage(10);
        attacker.setCurrentHp(21);
        attacker.setMaxHp(21);
        attacker.setAmount(1);

        attacker.attack(defender);

        assertThat(attacker.getCurrentHp()).isEqualTo(18);
        assertThat(attacker.getAmount()).isEqualTo(1);
    }

    @Test
    void attackerHas50PercentChanceToDoubleDamage() {
        Creature attacker = new Creature(new DamageBonusDamageCalculator(2), new RangedAttackStrategy());
        Creature defender = new Creature(new DefaultDamageCalculator(), new RangedAttackStrategy());
        defender.setCurrentHp(23);
        defender.setMaxHp(23);
        defender.setAmount(1);
        defender.setDamage(3);

        attacker.setDamage(4);
        attacker.setCurrentHp(21);
        attacker.setMaxHp(21);
        attacker.setAmount(1);

        attacker.attack(defender);

        assertThat(defender.getCurrentHp()).isEqualTo(15);
        assertThat(defender.getAmount()).isEqualTo(1);
    }


    @Test
    void testHpAfterDoubleCounter(){
        DefaultDamageCalculator damageCalculator = new DefaultDamageCalculator();
        Creature doubleCounterCreature = Creature.builder()
                .attack(10)
                .damage(6)
                .maxHp(10)
                .currentHp(10)
                .moveRange(4)
                .amount(1)
                .dmgCalc(damageCalculator)
                .attackStrategy(new DoubleCounterStrategy())
                .build();

        //attacker atakuje:

        Creature attacker = Creature.builder()
                .attack(10)
                .damage(6)
                .maxHp(10)
                .currentHp(10)
                .moveRange(4)
                .amount(1)
                .dmgCalc(damageCalculator)
                .attackStrategy(new RangedAttackStrategy())
                .build();

        //1 atak:
        attacker.attack(doubleCounterCreature);
        int hpLeftFirstAttack = attacker.getCurrentHp();

        //2 atak:
        attacker.attack(doubleCounterCreature);
        int hpLeftSecondAttack = attacker.getCurrentHp();

        assertThat(hpLeftSecondAttack).isLessThan(hpLeftFirstAttack);
    }


}