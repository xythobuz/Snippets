FasdUAS 1.101.10   ��   ��    k             l    6 ����  Z     6  	����  I     �� 
���� 0 appisrunning appIsRunning 
  ��  m       �    i T u n e s��  ��   	 k   	 2       O   	     r        l    ����  n        1    ��
�� 
pLyr  l    ����  1    ��
�� 
pTrk��  ��  ��  ��    o      ���� 0 	thelyrics 	theLyrics  m   	 
  �                                                                                  hook  alis    N  Deep Thought               ΍�H+     ]
iTunes.app                                                      A��_�        ����  	                Applications    ΍��      �^�~       ]  %Deep Thought:Applications: iTunes.app    
 i T u n e s . a p p    D e e p   T h o u g h t  Applications/iTunes.app   / ��     ��  Z    2  ��   =       o    ���� 0 	thelyrics 	theLyrics  m         � ! !    k    - " "  # $ # O    ( % & % r     ' ' ( ' l    % )���� ) n     % * + * 1   # %��
�� 
pDes + l    # ,���� , 1     #��
�� 
pTrk��  ��  ��  ��   ( o      ���� 0 thedesc theDesc & m     - -�                                                                                  hook  alis    N  Deep Thought               ΍�H+     ]
iTunes.app                                                      A��_�        ����  	                Applications    ΍��      �^�~       ]  %Deep Thought:Applications: iTunes.app    
 i T u n e s . a p p    D e e p   T h o u g h t  Applications/iTunes.app   / ��   $  . / . L   ) + 0 0 o   ) *���� 0 thedesc theDesc /  1 2 1 l  , ,�� 3 4��   3  tell application "iTunes"    4 � 5 5 2 t e l l   a p p l i c a t i o n   " i T u n e s " 2  6 7 6 l  , ,�� 8 9��   8 3 -	set theName to the name of the current track    9 � : : Z 	 s e t   t h e N a m e   t o   t h e   n a m e   o f   t h e   c u r r e n t   t r a c k 7  ; < ; l  , ,�� = >��   = 7 1	set theArtist to the artist of the current track    > � ? ? b 	 s e t   t h e A r t i s t   t o   t h e   a r t i s t   o f   t h e   c u r r e n t   t r a c k <  @ A @ l  , ,�� B C��   B  end tell    C � D D  e n d   t e l l A  E F E l  , ,�� G H��   G 3 -set theName to findReplace(" ", "_", theName)    H � I I Z s e t   t h e N a m e   t o   f i n d R e p l a c e ( "   " ,   " _ " ,   t h e N a m e ) F  J K J l  , ,�� L M��   L 7 1set theArtist to findReplace(" ", "_", theArtist)    M � N N b s e t   t h e A r t i s t   t o   f i n d R e p l a c e ( "   " ,   " _ " ,   t h e A r t i s t ) K  O P O l  , ,�� Q R��   Q J Dset theUrl to "http://lyrics.wikia.com/" & theArtist & ":" & theName    R � S S � s e t   t h e U r l   t o   " h t t p : / / l y r i c s . w i k i a . c o m / "   &   t h e A r t i s t   &   " : "   &   t h e N a m e P  T�� T l  , ,�� U V��   U  return theUrl    V � W W  r e t u r n   t h e U r l��  ��    L   0 2 X X o   0 1���� 0 	thelyrics 	theLyrics��  ��  ��  ��  ��     Y Z Y l     ��������  ��  ��   Z  [ \ [ i      ] ^ ] I      �� _���� 0 appisrunning appIsRunning _  `�� ` o      ���� 0 appname appName��  ��   ^ O     a b a E     c d c l   	 e���� e n    	 f g f 1    	��
�� 
pnam g 2   ��
�� 
prcs��  ��   d o   	 
���� 0 appname appName b m      h h�                                                                                  sevs  alis    �  Deep Thought               ΍�H+     ;System Events.app                                               ��A�Y        ����  	                CoreServices    ΍��      �A�9       ;   8   7  =Deep Thought:System: Library: CoreServices: System Events.app   $  S y s t e m   E v e n t s . a p p    D e e p   T h o u g h t  -System/Library/CoreServices/System Events.app   / ��   \  i j i l     ��������  ��  ��   j  k l k l     �� m n��   m D > From http://blog.mixable.de/applescript-findreplace-function/    n � o o |   F r o m   h t t p : / / b l o g . m i x a b l e . d e / a p p l e s c r i p t - f i n d r e p l a c e - f u n c t i o n / l  p q p l     �� r s��   r 7 1on findReplace(findText, replaceText, sourceText)    s � t t b o n   f i n d R e p l a c e ( f i n d T e x t ,   r e p l a c e T e x t ,   s o u r c e T e x t ) q  u v u l     �� w x��   w 6 0	set ASTID to AppleScript's text item delimiters    x � y y ` 	 s e t   A S T I D   t o   A p p l e S c r i p t ' s   t e x t   i t e m   d e l i m i t e r s v  z { z l     �� | }��   | 9 3	set AppleScript's text item delimiters to findText    } � ~ ~ f 	 s e t   A p p l e S c r i p t ' s   t e x t   i t e m   d e l i m i t e r s   t o   f i n d T e x t {   �  l     �� � ���   � 1 +	set sourceText to text items of sourceText    � � � � V 	 s e t   s o u r c e T e x t   t o   t e x t   i t e m s   o f   s o u r c e T e x t �  � � � l     �� � ���   � < 6	set AppleScript's text item delimiters to replaceText    � � � � l 	 s e t   A p p l e S c r i p t ' s   t e x t   i t e m   d e l i m i t e r s   t o   r e p l a c e T e x t �  � � � l     �� � ���   � ( "	set sourceText to "" & sourceText    � � � � D 	 s e t   s o u r c e T e x t   t o   " "   &   s o u r c e T e x t �  � � � l     �� � ���   � 6 0	set AppleScript's text item delimiters to ASTID    � � � � ` 	 s e t   A p p l e S c r i p t ' s   t e x t   i t e m   d e l i m i t e r s   t o   A S T I D �  � � � l     �� � ���   �  	return sourceText    � � � � $ 	 r e t u r n   s o u r c e T e x t �  ��� � l     �� � ���   �  end findReplace    � � � �  e n d   f i n d R e p l a c e��       �� � � ��� ���   � ���������� 0 appisrunning appIsRunning
�� .aevtoappnull  �   � ****�� 0 	thelyrics 	theLyrics�� 0 thedesc theDesc � �� ^���� � ����� 0 appisrunning appIsRunning�� �� ���  �  ���� 0 appname appName��   � ���� 0 appname appName �  h����
�� 
prcs
�� 
pnam�� � 	*�-�,�U � �� ����� � ���
�� .aevtoappnull  �   � **** � k     6 � �  ����  ��  ��   �   � 	 �� ������  ������ 0 appisrunning appIsRunning
�� 
pTrk
�� 
pLyr�� 0 	thelyrics 	theLyrics
�� 
pDes�� 0 thedesc theDesc�� 7*�k+  .� 	*�,�,E�UO��  � 	*�,�,E�UO�OPY �Y h
�� 
msng � � � �   ascr  ��ޭ