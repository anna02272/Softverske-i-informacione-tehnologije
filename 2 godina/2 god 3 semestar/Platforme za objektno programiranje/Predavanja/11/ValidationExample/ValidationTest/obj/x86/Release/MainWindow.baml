   M S B A M L   `   `   `  ���� I  EValidationTest, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null/clr-namespace:ValidationTestValidationTest      ValidationTest.MainWindow   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     .localclr-namespace:ValidationTest       � Title$  
MainWindow��$	��468��$	��525�� � 	Resources    TextBoxErrorTemplate'  !ValidationTest.ValidatorConverter  	converter��%}   &	        &	 d     �� .R�]� ��. .+��� $��Right=�
��� ���$
��12pt(�!!!�� 
��� � �$��1q�._���   .��� .+��� -   $��gbPodaci��$��Podaci o studentu��$	��376��$
��Left=�$��	12,12,0,0q�$	��Top=�$	��462��.��� $	��482��.+��� -   $��lbIme��$��23��$
��Left=�$��	10,13,0,0q�$	��Ime��$	��Top=��� -   $��tbIme��$��23��$
��Left=�$��	79,10,0,0q�$	��Top=�$	��120������  �� Path$	 Ime��� -   $��	lbPrezime��$��23��$
��Left=�$��	10,42,0,0q�$��Prezime��$	��Top=��� -   $��	tbPrezime��$��23��$
��Left=�$��	79,39,0,0q�$	��Top=�$	��120������ $ Prezime��� -   $��lbGodinaUpisa��$��23��$
��Left=�$��	10,71,0,0q�$��Godina upisa��$	��Top=��� -   $��tbGodinaUpisa��$��23��$
��Left=�$��	79,68,0,0q�$	��Top=�$	��120������ $ GodinaUpisa� �� ValidatesOnExceptions . �� -   $��lbEmail1��$��23��$
��Left=�$��
10,100,0,0q�$��E-mail 1��$	��Top=��� -	   $��tbEmail1��$��23��$
��Left=�$��	79,97,0,0q�$	��Top=�$	��120��.������ $ Email� �� ValidationRules (   "ValidationTest.EMailValidationRule  .���� -
   $��lbEmail2��$��23��$
��Left=�$��
10,129,0,0q�$��E-mail 2��$	��Top=��� -   $��tbEmail2�� =� ErrorTemplate# [  $��23��$
��Left=�$��
79,126,0,0q�$	��Top=�$	��120��.������ $ Email�   .���� -   $��lbEmail3��$��23��$
��Left=�$��
10,158,0,0q�$��E-mail 3��$	��Top=��� -   $��tbEmail3��$��23��$
��Left=�$��
79,155,0,0q�$	��Top=�$	��120��.������ $ Email� �� UpdateSourceTrigger$ PropertyChanged=�   .���� $��
79,185,0,0q�
���  ��$
��Bold"�$
��Left=�$	��377��$��23��$	��Top=�����  �� ElementName$ tbEmail3��$ (Validation.Errors)� �� 	Converter# [ �� $��
79,215,0,0q�
���  ��$
��Bold"�$
��Left=�$	��377��$��23��$	��Top=����� $ tbEmail3��$2 ,(Validation.Errors).CurrentItem.ErrorContent��� -   $��lbEmail4��$��23��$
��Left=�$��
10,249,0,0q�$��E-mail 4��$	��Top=��� -   $��tbEmail4��$��23��$
��Left=�$��
79,246,0,0q�$	��Top=�$	��120��.��.���� $ Email�$ PropertyChanged=�	 �� ValidatesOnDataErrors	 .    �� $��
79,280,0,0q�
���  ��$
��Bold"�$
��Left=�$	��377��$��23��$	��Top=����� $ tbEmail3��$2 ,(Validation.Errors).CurrentItem.ErrorContent�