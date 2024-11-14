package org.example;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@ToString
public class Creature {
    private int attack;
    private int defence;
    private int damage;
    private int maxHp;
    private int currentHp;
    private int amount;
    private int moveRange;

    private DefaultDamageCalculator dmgCalc;
    private AttackStrategy attackStrategy;

    public Creature(DefaultDamageCalculator dmgCalc, AttackStrategy attackStrategy)
    {
        this.dmgCalc = dmgCalc;
        this.attackStrategy = attackStrategy;
    }

    public void attack(Creature defender) {
        attackStrategy.attack(this, defender);
        attackStrategy.counterAttack(this, dmgCalc.calculateDamage(defender, this));
    }

    public void applyDamage(int aDamageToApply) {
        int stackToKill = aDamageToApply / maxHp;
        amount = amount - stackToKill;
        int restOfDamage = aDamageToApply - (stackToKill * maxHp);
        currentHp = currentHp - restOfDamage;
    }
}
