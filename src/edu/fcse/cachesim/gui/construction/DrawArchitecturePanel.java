package edu.fcse.cachesim.gui.construction;

import edu.fcse.cachesim.interfaces.CPUCore;
import edu.fcse.cachesim.interfaces.CacheLevel;
import edu.fcse.cachesim.interfaces.Referable;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import javax.swing.JPanel;

public class DrawArchitecturePanel extends JPanel {

    private Set<String> coreTags;
    private Map<String, Referable> elements;
    private Map<String, Set<String>> l3tol2;
    private Map<String, Set<String>> l2tol1;
    private Map<String, Set<String>> l1toCores;
    private Map<String, Integer> elementToNumCores;
    private int numCores;

    public DrawArchitecturePanel(Map<String, CPUCore> createdCores) {
        coreTags = new HashSet<>();
        elements = new HashMap<>();
        l3tol2 = new HashMap<>();
        l2tol1 = new HashMap<>();
        l1toCores = new HashMap<>();
        elementToNumCores = new HashMap<>();
        this.updateStuff(createdCores);
    }

    public final void updateStuff(Map<String, CPUCore> createdCores) {
        this.numCores = createdCores.size();
        for (String coreTag : createdCores.keySet()) {
            coreTags.add(coreTag);
            CPUCore current = createdCores.get(coreTag);
            elements.put(coreTag, current);
            CacheLevel lvl = current.getLevel(1);
            if (elementToNumCores.containsKey(lvl.getTag())) {
                elementToNumCores.put(lvl.getTag(), elementToNumCores.get(lvl.getTag()) + 1);
            } else {
                elementToNumCores.put(lvl.getTag(), 1);
            }
            if (!l1toCores.containsKey(lvl.getTag())) {
                l1toCores.put(lvl.getTag(), new HashSet<String>());
            }
            l1toCores.get(lvl.getTag()).add(coreTag);
            String lvl1Tag = lvl.getTag();
            elements.put(lvl.getTag(), lvl);

            lvl = current.getLevel(2);
            if (elementToNumCores.containsKey(lvl.getTag())) {
                elementToNumCores.put(lvl.getTag(), elementToNumCores.get(lvl.getTag()) + 1);
            } else {
                elementToNumCores.put(lvl.getTag(), 1);
            }
            if (!l2tol1.containsKey(lvl.getTag())) {
                l2tol1.put(lvl.getTag(), new HashSet<String>());
            }
            l2tol1.get(lvl.getTag()).add(lvl1Tag);
            String lvl2Tag = lvl.getTag();
            elements.put(lvl.getTag(), lvl);

            lvl = current.getLevel(3);
            if (elementToNumCores.containsKey(lvl.getTag())) {
                elementToNumCores.put(lvl.getTag(), elementToNumCores.get(lvl.getTag()) + 1);
            } else {
                elementToNumCores.put(lvl.getTag(), 1);
            }
            elements.put(lvl.getTag(), lvl);
            if (!l3tol2.containsKey(lvl.getTag())) {
                l3tol2.put(lvl.getTag(), new HashSet<String>());
            }
            l3tol2.get(lvl.getTag()).add(lvl2Tag);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension panelSize = this.getSize();
        int coreWidth = (int) (0.8 * (panelSize.getWidth() / numCores));
        int coreHeight = (int) (0.98 * panelSize.getHeight());
        if (coreWidth > 1.2 * coreHeight) {
            coreWidth = (int) 1.2 * coreHeight;
        }
        if (coreWidth < 50 || coreHeight < 50) {
            g.drawString("Expand window", ((int) panelSize.getWidth() / 2) - 25, 10);
            return;
        }
        // height total = 25 parts
        // core = l1 = 4parts = 16%
        //l2 = 5 = 20%, l3 = 6 = 24%
        //lines = 2parts = 8%
        double coreCircleRadius = 0.16 * coreHeight;
        drawAllL3s(g, coreWidth, coreHeight);
    }

    private void drawCores(Graphics g, int coreWidth, int coreHeight, Queue<String> l1DrawOrder) {
        int currentX = 0;
        int l3Height = (int) (0.24 * coreHeight);
        int lineHeight = (int) (0.08 * coreHeight);
        int l2Height = (int) (0.2 * coreHeight);
        int l1Height = (int) (0.16 * coreHeight);
        int coreCircleHeight = l1Height;
        List<Integer> exesForLines = new LinkedList<>();
        int currentY = coreHeight - l3Height - 3 * lineHeight - l2Height - l1Height - coreCircleHeight;
        while (!l1DrawOrder.isEmpty()) {
            String l1Tag = l1DrawOrder.poll();
            Set<String> coreTags = l1toCores.get(l1Tag);
            for (String coreTag : coreTags) {
                CPUCore core = (CPUCore) elements.get(coreTag);
                g.drawOval(currentX + 5, currentY, coreCircleHeight, coreCircleHeight);
                int centerX = currentX + 5 + ((int) coreCircleHeight / 2);
                exesForLines.add(centerX);
                int tagWidth = (int) g.getFontMetrics().getStringBounds(core.getTag(), g).getWidth();
                int centerOfRect = currentX + 5 + (coreCircleHeight / 2);
                g.drawString(core.getTag(), (int) (centerOfRect - (tagWidth / 2.0)), (int) (currentY - lineHeight));
                currentX += coreWidth;
            }
        }
        drawLines(g, currentY + coreCircleHeight, exesForLines, lineHeight);
    }
    private final Comparator<CacheLevel> prioComparator = new Comparator<CacheLevel>() {

        @Override
        public int compare(CacheLevel o1, CacheLevel o2) {
            return Integer.compare(elementToNumCores.get(o2.getTag()), elementToNumCores.get(o1.getTag()));
        }
    };

    private void drawAllL3s(Graphics g, int coreWidth, int coreHeight) {
        PriorityQueue<CacheLevel> podredeni = new PriorityQueue<>(prioComparator);
        for (String l3tag : l3tol2.keySet()) {
            CacheLevel l3 = (CacheLevel) elements.get(l3tag);
            podredeni.add(l3);
        }
        int currentX = 0;
        int l3Height = (int) (0.24 * coreHeight);
        int currentY = coreHeight - l3Height;
        Queue<String> l3DrawOrder = new LinkedList<>();
        while (!podredeni.isEmpty()) {
            CacheLevel current = podredeni.poll();
            int numTimes = elementToNumCores.get(current.getTag());
            g.drawRect(currentX + 5, currentY, (numTimes * coreWidth) - 5, l3Height);
            int tagWidth = (int) g.getFontMetrics().getStringBounds(current.getTag(), g).getWidth();
            int centerOfRect = currentX + 5 + ((numTimes * coreWidth - 5) / 2);
            g.drawString(current.getTag(), (int) (centerOfRect - (tagWidth / 2.0)), (int) (currentY + (l3Height / 2)));
            currentX += numTimes * coreWidth;
            l3DrawOrder.add(current.getTag());
        }
        drawAllL2s(g, coreWidth, coreHeight, l3DrawOrder);
    }

    private void drawAllL2s(Graphics g, int coreWidth, int coreHeight, Queue<String> l3DrawOrder) {

        int currentX = 0;
        Queue<String> l2DrawOrder = new LinkedList<>();
        List<Integer> exesForLines = new LinkedList<>();
        int l3Height = (int) (0.24 * coreHeight);
        int lineHeight = (int) (0.08 * coreHeight);
        int l2Height = (int) (0.2 * coreHeight);
        int currentY = coreHeight - l3Height - lineHeight - l2Height;
        while (!l3DrawOrder.isEmpty()) {
            String l3Tag = l3DrawOrder.poll();
            Set<String> l2tags = l3tol2.get(l3Tag);
            PriorityQueue<CacheLevel> podredeni = new PriorityQueue<>(prioComparator);
            for (String l2 : l2tags) {
                podredeni.add((CacheLevel) elements.get(l2));
            }

            while (!podredeni.isEmpty()) {
                CacheLevel currentL2 = podredeni.poll();
                int numTimes = elementToNumCores.get(currentL2.getTag());
                g.drawRect(currentX + 5, currentY, (numTimes * coreWidth) - 5, l2Height);
                int tagWidth = (int) g.getFontMetrics().getStringBounds(currentL2.getTag(), g).getWidth();
                int centerOfRect = currentX + 5 + ((numTimes * coreWidth - 5) / 2);
                g.drawString(currentL2.getTag(), (int) (centerOfRect - (tagWidth / 2.0)), (int) (currentY + (l2Height / 2)));
                currentX += numTimes * coreWidth;
                l2DrawOrder.add(currentL2.getTag());
                exesForLines.add(centerOfRect);
            }
        }
        drawLines(g, currentY + l2Height, exesForLines, lineHeight);
        drawAllL1s(g, coreWidth, coreHeight, l2DrawOrder);
    }

    private void drawAllL1s(Graphics g, int coreWidth, int coreHeight, Queue<String> l2DrawOrder) {
        int currentX = 0;
        int l3Height = (int) (0.24 * coreHeight);
        int lineHeight = (int) (0.08 * coreHeight);
        int l2Height = (int) (0.2 * coreHeight);
        int l1Height = (int) (0.16 * coreHeight);
        List<Integer> exesForLines = new LinkedList<>();
        Queue<String> l1DrawOrder = new LinkedList<>();
        int currentY = coreHeight - l3Height - 2 * lineHeight - l2Height - l1Height;
        while (!l2DrawOrder.isEmpty()) {
            String l2Tag = l2DrawOrder.poll();
            Set<String> l1tags = l2tol1.get(l2Tag);
            PriorityQueue<CacheLevel> podredeni = new PriorityQueue<>(prioComparator);
            for (String l1 : l1tags) {
                podredeni.add((CacheLevel) elements.get(l1));
            }

            while (!podredeni.isEmpty()) {
                CacheLevel currentL1 = podredeni.poll();
                int numTimes = elementToNumCores.get(currentL1.getTag());
                g.drawRect(currentX + 5, currentY, (numTimes * coreWidth) - 5, l1Height);
                int centerX = currentX + 5 + ((int) (numTimes * coreWidth - 5) / 2);
                exesForLines.add(centerX);
                int tagWidth = (int) g.getFontMetrics().getStringBounds(currentL1.getTag(), g).getWidth();
                int centerOfRect = currentX + 5 + ((numTimes * coreWidth - 5) / 2);
                g.drawString(currentL1.getTag(), (int) (centerOfRect - (tagWidth / 2.0)), (int) (currentY + (l1Height / 2)));
                l1DrawOrder.add(currentL1.getTag());
                currentX += numTimes * coreWidth;
            }
        }
        drawLines(g, currentY + l1Height, exesForLines, lineHeight);
        drawCores(g, coreWidth, coreHeight, l1DrawOrder);
    }

    private void drawLines(Graphics g, int starty, List<Integer> exes, int lineHeight) {
        for (int startx : exes) {
            g.drawLine(startx, starty, startx, starty + lineHeight);
        }
    }

}
