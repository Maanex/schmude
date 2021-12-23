package me.maanex.schmude.core.customcontent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.MultipleFacing;

public class MushroomStates {

  public static final BlockFace[] BASE_FACES = new BlockFace[] {
    BlockFace.DOWN,
    BlockFace.EAST,
    BlockFace.NORTH,
    BlockFace.SOUTH,
    BlockFace.UP,
    BlockFace.WEST
  };

  public static final String[] BROWN_MUSHROOM_LIST_RAW = new String[] {
    "000001",
    "000100",
    "000101",
    "001000",
    "001001",
    "001100",
    "001101",
    "001110",
    "001111",
    "010000",
    "010001",
    "010011",
    "010100",
    "010101",
    "010111",
    "011000",
    "011001",
    "011011",
    "011100",
    "011101",
    "011110",
    "011111",
    "100000",
    "100001",
    "100010",
    "100011",
    "100100",
    "100101",
    "100110",
    "100111",
    "101000",
    "101001",
    "101010",
    "101011",
    "101100",
    "101101",
    "101110",
    "101111",
    "110000",
    "110001",
    "110010",
    "110011",
    "110100",
    "110101",
    "110110",
    "110111",
    "111000",
    "111001",
    "111010",
    "111011",
    "111100",
    "111101",
    "111110"
  };

  @SuppressWarnings("unchecked")
  public static final List<BlockFace>[] BROWN_MUSHROOM_LIST = (List<BlockFace>[]) Arrays.stream(BROWN_MUSHROOM_LIST_RAW)
    .map((String s) -> {
      List<BlockFace> out = new ArrayList<>();
      if (s.charAt(0) == '1') out.add(BlockFace.DOWN);
      if (s.charAt(1) == '1') out.add(BlockFace.EAST);
      if (s.charAt(2) == '1') out.add(BlockFace.NORTH);
      if (s.charAt(3) == '1') out.add(BlockFace.SOUTH);
      if (s.charAt(4) == '1') out.add(BlockFace.UP);
      if (s.charAt(5) == '1') out.add(BlockFace.WEST);
      return out;
    })
    .toArray(List<?>[]::new);

  //

  public static void applyBrownMushroomToMultiface(MultipleFacing data, int index) {
    String s = BROWN_MUSHROOM_LIST_RAW[index];
    data.setFace(BlockFace.DOWN, s.charAt(0) == '1');
    data.setFace(BlockFace.EAST, s.charAt(1) == '1');
    data.setFace(BlockFace.NORTH, s.charAt(2) == '1');
    data.setFace(BlockFace.SOUTH, s.charAt(3) == '1');
    data.setFace(BlockFace.UP, s.charAt(4) == '1');
    data.setFace(BlockFace.WEST, s.charAt(5) == '1');
  }

  public static int getIdFromBrownMushroomData(MultipleFacing data) {
    StringBuilder builder = new StringBuilder();
    for (BlockFace f : BASE_FACES)
    builder.append(data.hasFace(f) ? '1' : '0');
  
    String search = builder.toString();
    for (int i = 0; i < BROWN_MUSHROOM_LIST_RAW.length; i++) {
      if (search.equals(BROWN_MUSHROOM_LIST_RAW[i]))
        return i;
    }

    return -1;
  }

}
