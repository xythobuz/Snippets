" CTRL-U in insert mode deletes a lot.  Use CTRL-G u to first break undo,
" so that you can undo CTRL-U after inserting a line break.
inoremap <C-U> <C-G>u<C-U>

" In many terminal emulators the mouse works just fine, thus enable it.
if has('mouse')
  set mouse=a
endif

" Show encoding in Statusline
if has("statusline")
 set statusline=%<%f\ %h%m%r%=%{\"[\".(&fenc==\"\"?&enc:&fenc).((exists(\"+bomb\")\ &&\ &bomb)?\",B\":\"\").\"]\ \"}%k\ %-14.(%l,%c%V%)\ %P
endif

colorscheme diablo3
set hidden
set ofu=syntaxcomplete#Complete

map <silent> <C-a> :call BufferList()<CR>

let g:BufferListWidth = 25
let g:BufferListMaxWidth = 50
hi BufferSelected term=reverse ctermfg=white ctermbg=red cterm=bold
hi BufferNormal term=NONE ctermfg=black ctermbg=darkcyan cterm=NONE

set autoindent smartindent		" always set autoindenting on
set autowrite
set tabstop=4
set shiftwidth=4
set showmatch
set guioptions-=T
set incsearch
set nocompatible
set backspace=indent,eol,start
set backup
set backupdir=~/.vim_backup
set history=50
set ruler
set showcmd
set incsearch
set autochdir
set number
set smarttab
set undolevels=1000
set complete=.,w,b,u,U,t,i,d
set wildmode=longest:full
set wildmenu
set ignorecase
set hlsearch

filetype plugin on
let filetype_m='objc'

syntax on

"inoremap ' ''<Left>
"inoremap " ""<Left>
"inoremap ( ()<Left>
"inoremap < <><Left>
"inoremap { {}<Left>
"inoremap [ []<Left>

if has("gui_running")
	set columns=100
	set transparency=10
	set guifont=Bitstream\ Vera\ Sans\ Mono:h13
endif
