package me.timothy.ats2.lib.blocktracers.lib;

import com.sun.javaws.exceptions.InvalidArgumentException;
import me.timothy.ats2.lib.Reference;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by CyanThunderMC on 6/15/2016.
 */
public class BlockTrace {
    //TODO: FINISH
    int maxTraces;

    public Collection<Block> traceBlocks(Block block) {
        return traceBlocks(block, false);
    }

    public Collection<Block> traceBlocks(Block block, boolean traceDiagonally) {
        reload();
        Collection<Block> blocks = new ArrayList<>();
        blocks.add(block);

        //Start Tracing!

        //if ()

        return blocks;
    }

    public Collection<Block> traceBlocks(Block block, int radius) {
        return traceBlocks(block, radius, true);
    }

    public Collection<Block> traceBlocks(Block block, int radius, boolean connected) {
        Collection<Block> blockCollection = new ArrayList<>();
        blockCollection.add(block);

        return traceBlocks(block, radius, connected, blockCollection);
    }

    public Collection<Block> traceBlocks(Block block, int radius, boolean connected, Collection<Block> connectables) {
        reload();
        Collection<Block> blocks = new ArrayList<>();
        blocks.add(block);

        //Start Tracing!

        return blocks;
    }

    private boolean isBlockConnected(Block a, Block b) {
        Collection<Block> blockCollection = new ArrayList<>();
        blockCollection.add(a);
        blockCollection.add(b);

        return isBlockConnected(a, b, blockCollection);
    }

    private boolean isBlockConnected(Block a, Block b, Collection<Block> connectables) {
        if (connectables == null) {
            throw new IllegalArgumentException("Illegal Argument 'connectables'");
        }

        //Convert Blocks to Material
        boolean test;
        Collection<Material> c = new ArrayList<>();
        for (Block block : connectables) {
            c.add(block.getType());
        }

        //Do some checks


        return false;
    }

    public void reload() {
        maxTraces = Reference.BlockTracer.maximumBlockTraces;
    }
}
