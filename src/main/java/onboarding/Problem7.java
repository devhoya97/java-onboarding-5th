package onboarding;

import java.util.*;
import java.util.stream.Collectors;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = Collections.emptyList();
        Map<String, Integer> recommendMapOfUser = getRecommendMapOfUser(user, friends, visitors);
        answer = getSortedRecommendListOfUser(recommendMapOfUser);
        return answer;
    }
    public static List<String> getSortedRecommendListOfUser(Map<String, Integer> recommendMapOfUser) {
//        List<String> sortedRecommendListOfUser = recommendMapOfUser.entrySet().stream()
//                .filter(entry -> entry.getValue() > 0).sorted().map(Map.Entry::getKey).collect(Collectors.toList());
        List<String> sortedRecommendListOfUser = recommendMapOfUser.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed()
                        .thenComparing(Map.Entry<String, Integer>::getKey)).
                map(Map.Entry::getKey).limit(5).collect(Collectors.toList());
        return sortedRecommendListOfUser;
    }
    public static Map<String, Integer> getRecommendMapOfUser(String user, List<List<String>> friends, List<String> visitors) {
        Map<String, Set<String>> friendshipMap = getFriendshipMap(friends);
        Map<String, Integer> recommendMapOfUserWithVisitors = getRecommendMapOfUserWithVisitors(user, visitors, friendshipMap);
        Map<String, Integer> recommendMapOfUser = getRecommendMapOfUserWithFriends(user, friendshipMap);
        for (Map.Entry<String, Integer> entry : recommendMapOfUserWithVisitors.entrySet()){
            int recommendPoint = recommendMapOfUser.getOrDefault(entry.getKey(), 0);
            recommendMapOfUser.put(entry.getKey(), recommendPoint+entry.getValue());
        }
        return recommendMapOfUser;
    }
    public static Map<String, Integer> getRecommendMapOfUserWithVisitors(String user, List<String> visitors, Map<String, Set<String>> friendshipMap) {
        Set<String> friendsSetOfUser = friendshipMap.get(user);
        Map<String, Integer> recommendMapOfUserWithVisitors = new HashMap<>();
        for (String visitor : visitors) {
            if (friendsSetOfUser.contains(visitor))
                continue;
            int recommendPoint = recommendMapOfUserWithVisitors.getOrDefault(visitor, 0);
            recommendMapOfUserWithVisitors.put(visitor, recommendPoint+1);
        }
        return recommendMapOfUserWithVisitors;
    }
    public static Map<String, Integer> getRecommendMapOfUserWithFriends(String user, Map<String, Set<String>> friendshipMap) {
        Map<String, Integer> recommendMapOfUserWithFriends = new HashMap<>();
        Set<String> friendsSetOfUser = friendshipMap.get(user);
        for(String friend : friendsSetOfUser){
            Set<String> friendsSetOfFriend = friendshipMap.get(friend);
           addRecommendMapOfUserWithFriends(user, friendsSetOfUser, friendsSetOfFriend, recommendMapOfUserWithFriends);
       }
        return recommendMapOfUserWithFriends;
    }
    public static void addRecommendMapOfUserWithFriends(String user, Set<String> friendsSetOfUser, Set<String> friendsSetOfFriend, Map<String, Integer> recommendMapOfUserWithFriends) {
        for (String friendOfFriend : friendsSetOfFriend) {
            if (user.equals(friendOfFriend) || friendsSetOfUser.contains(friendOfFriend))
                continue;
            int recommendPoint = recommendMapOfUserWithFriends.getOrDefault(friendOfFriend, 0);
            recommendMapOfUserWithFriends.put(friendOfFriend, recommendPoint+10);
        }
    }
    public static Map<String, Set<String>> getFriendshipMap (List<List<String>> friends) {
        Map<String, Set<String>> friendshipMap = new HashMap<>();
        for(List<String> friendship : friends) {
            addFriendshipMap(friendshipMap, friendship);
        }
        return friendshipMap;
    }
    public static void addFriendshipMap (Map<String, Set<String>> friendshipMap, List<String> friendship) {
        String friend1 = friendship.get(0);
        String friend2 = friendship.get(1);
        Set<String> friendsSetOffriend1 = friendshipMap.getOrDefault(friend1, new HashSet<>());
        Set<String> friendsSetOffriend2 = friendshipMap.getOrDefault(friend2, new HashSet<>());
        friendsSetOffriend1.add(friend2);
        friendsSetOffriend2.add(friend1);
        friendshipMap.put(friend1, friendsSetOffriend1);
        friendshipMap.put(friend2, friendsSetOffriend2);
    }
}
