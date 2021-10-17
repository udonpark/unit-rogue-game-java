package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this capability to be hostile towards something (e.g., to be attacked by enemy)
    ATTACK_LIMITED, // use this capability to restrict attacks from a player
    STUNNED, // stunned status
    RAGE_MODE, // yhorms rage mode
    WEAK_TO_STORM_RULER, // only yhorm takes certain damage from storm ruler
    WAS_REVIVED,
    KILLED_YHORM, // proves that the player has killed yhorm
    KILLED_ALDRICH,// proves that the player has killed aldrich
    CAN_OPEN_CHEST // called when someone can open chest
}