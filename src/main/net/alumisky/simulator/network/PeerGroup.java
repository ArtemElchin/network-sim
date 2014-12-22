package net.alumisky.simulator.network;

/**
 * Peers self-organize into Peer Groups.
 * A peer group is a collection of peers that have a common set of interests.
 *
 * @author Renat.Hilmanov
 */
public interface PeerGroup extends NetworkEntity<PeerID> {

    /**
     * Add peer to group.
     *
     * @param peer to add
     */
    void add(Peer peer);

    /**
     * Get peer by peer id.
     *
     * @param id id of peer to return
     * @return peer with specified id
     */
    Peer getPeer(PeerID id);
}
