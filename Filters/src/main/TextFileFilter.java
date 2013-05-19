package main;

import javax.swing.filechooser.FileFilter;

// Фильтр картинок
class TextFileFilter extends FileFilter
{
    private String ext;
    public TextFileFilter(String ext)
    {
        this.ext=ext;
    }
    @Override
    public boolean accept(java.io.File file)
    {
         if (file.isDirectory()) return true;
         return (file.getName().endsWith(ext));
    }
    @Override
    public String getDescription()
    {
         return "*"+ext;
    }
   
}
